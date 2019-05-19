package com.eternal.web.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.eternal.web.dto.response.ErrorInfoResponse;
import com.eternal.web.dto.response.ErrorInfoResponse.ErrorInfo;
import com.eternal.web.message.MessageCode;
import com.eternal.web.message.MessageSourceImpl;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.RequiredArgsConstructor;

/**
 * 業務処理内のエラー処理クラス
 *
 * @author taiki0304
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /** {@link MessageSource} */
    private final MessageSourceImpl messageSource;

    /**
     * 業務例外{@link ServiceException}の処理
     *
     * @param {@link ServiceException} e
     * @param {@link WebRequest} request
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleExceptionInternal(ServiceException e, WebRequest request) {
        return super.handleExceptionInternal(e, errorInfoResponse(new ErrorInfo(e.getErrorCode(), e.getMessage())),
                null, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Springboot内の{@link Valid}で定義している例外の処理
     *
     * @param {@link MethodArgumentNotValidException} e
     * @param {@link HttpHeaders} heders
     * @param {@link HttpStatus} status
     * @param {@link WebRequest} request
     * @return {@link ResponseEntity}
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorInfo> errorInfoList = e.getBindingResult().getFieldErrors().stream().map(
                er -> new ErrorInfo(MessageCode.VALIDATE_EXCEPTION, messageSource.getMessage(er, Locale.getDefault())))
                .collect(Collectors.toList());
        return super.handleExceptionInternal(e, new ErrorInfoResponse(errorInfoList), null, HttpStatus.BAD_REQUEST,
                request);
    }

    /**
     * {@link BindException}の処理
     *
     * @param {@link BindException} e
     * @param {@link HttpHeaders} headers
     * @param {@link HttpStatus} status
     * @param {@link WebRequest} request
     * @return {@link ResponseEntity}
     */
    @Override
    public ResponseEntity<Object> handleBindException(BindException e, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        List<ErrorInfo> errorInfoList = e.getBindingResult().getFieldErrors().stream().map(
                er -> new ErrorInfo(MessageCode.TYPE_MISMATCH_EXCEPTION,
                        messageSource.getMessage(MessageCode.TYPE_MISMATCH_EXCEPTION, Collections.singletonList(er.getField()))))
                .collect(Collectors.toList());
        return super.handleExceptionInternal(e, new ErrorInfoResponse(errorInfoList), null, HttpStatus.BAD_REQUEST,
                request);
    }

    /**
     * {@link JsonMappingException}の処理
     *
     * @param {@link JsonMappingException} e
     * @param {@link HttpHeaders} headers
     * @param {@link WebRequest}  request
     * @return {@link ResponseEntity}
     */
    private ResponseEntity<Object> handleJsonMappingException(JsonMappingException e, HttpHeaders headers,
            WebRequest request) {
        List<ErrorInfo> errorInfoList = e.getPath().stream()
                .map(p -> new ErrorInfo(MessageCode.TYPE_MISMATCH_EXCEPTION,
                        messageSource.getMessage(e.getOriginalMessage(), Collections.singletonList(p.getFieldName()))))
                .collect(Collectors.toList());
        return super.handleExceptionInternal(e, new ErrorInfoResponse(errorInfoList), headers, HttpStatus.BAD_REQUEST,
                request);
    }

    /**
     * その他の例外処理(システムエラー)
     *
     * @param {@link Exception} e
     * @param {@link WebRequest} request
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception e, WebRequest request) {
        return super.handleExceptionInternal(e,
                errorInfoResponse(
                        new ErrorInfo(MessageCode.EXCEPTION, messageSource.getMessage(MessageCode.EXCEPTION))),
                null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    /**
     * 包括的なエラー処理クラス
     *
     * @param {@link Exception} e
     * @param {@link Object} body
     * @param {@link HttpHeaders} headers
     * @param {@link HttpStatus} status
     * @param {@link WebRequest} request
     * @return {@link ResponseEntity}
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        if (e instanceof HttpMessageNotReadableException) {
            if (e.getCause() instanceof JsonMappingException) {
                // JsonMappingExceptionの処理を移譲する
                return  handleJsonMappingException((JsonMappingException) e.getCause(), headers, request);
            }
        }
        return super.handleExceptionInternal(e, body, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ErrorInfoResponse errorInfoResponse(ErrorInfo error) {
        List<ErrorInfo> errorInfoList = new ArrayList<>();
        errorInfoList.add(error);
        return new ErrorInfoResponse(errorInfoList);
    }
}
