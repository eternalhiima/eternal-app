package com.eternal.web.exception;

import org.assertj.core.util.Arrays;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.eternal.web.dto.response.ErrorInfoResponse;
import com.eternal.web.message.MessageCode;
import com.eternal.web.message.MessageSourceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /** メッセージソース */
    private final MessageSourceImpl messageSource;

    /**
     * EternalExceptionの処理
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(EternalException.class)
    public ResponseEntity<Object> handleExceptionInternal(EternalException e, WebRequest request) {
        ErrorInfoResponse error = ErrorInfoResponse.builder()
                .code(e.getErrorCode())
                .message(messageSource.getMessage(e.getErrorCode())).build();
        return super.handleExceptionInternal(e, error, null, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Springboot内の{#link @Valid}で定義している例外の処理
     * @param e
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorInfoResponse error = ErrorInfoResponse.builder()
                .code(MessageCode.API9000E)
                .message(messageSource.getMessage(MessageCode.API9000E, Arrays.asObjectArray(e.getBindingResult().getTarget()))).build();
        return super.handleExceptionInternal(e, error, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    /**
     * その他の例外処理
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception e, WebRequest request) {
        ErrorInfoResponse error = ErrorInfoResponse.builder()
                .code(MessageCode.API9999E)
                .message(messageSource.getMessage(MessageCode.API9999E)).build();
        return super.handleExceptionInternal(e, error, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
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
        log.error("[error]" + e.getMessage());
        return super.handleExceptionInternal(e, body, headers, HttpStatus.BAD_REQUEST, request);
    }
}
