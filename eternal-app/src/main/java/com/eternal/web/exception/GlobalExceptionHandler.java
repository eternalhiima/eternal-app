package com.eternal.web.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.eternal.web.dto.response.ErrorInfoResponse;
import com.eternal.web.dto.response.ErrorInfoResponse.ErrorInfo;
import com.eternal.web.message.MessageCode;
import com.eternal.web.message.MessageSourceImpl;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /** {@link MessageSource} */
    private final MessageSourceImpl messageSource;

    /**
     * 業務例外{@link EternalException}の処理
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(EternalException.class)
    public ResponseEntity<Object> handleExceptionInternal(EternalException e, WebRequest request) {
        return super.handleExceptionInternal(e, errorInfoResponse(new ErrorInfo(e.getErrorCode(), e.getMessage())),
                null, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Springboot内の{@link Valid}で定義している例外の処理
     *
     * @param e
     * @param request
     * @return
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
     * その他の例外処理
     *
     * @param e
     * @param request
     * @return
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
     * @param Exception e
     * @param Object body
     * @param HttpHeaders headers
     * @param HttpStatus status
     * @param WebRequest request
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(e, body, headers, HttpStatus.BAD_REQUEST, request);
    }

    private ErrorInfoResponse errorInfoResponse(ErrorInfo error) {
        List<ErrorInfo> errorInfoList = new ArrayList<>();
        errorInfoList.add(error);
        return new ErrorInfoResponse(errorInfoList);
    }
}
