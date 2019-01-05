package com.eternal.web.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.eternal.web.dto.response.ErrorInfoResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


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
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorInfoResponse error;
        if (e instanceof EternalException) {
            error =  ErrorInfoResponse.builder()
                    .code(((EternalException) e).getErrorCode())
                    .message("[error] customized message.").build();
        }

        // TODO: 想定される例外クラスを記述する
        error = ErrorInfoResponse.builder()
                .code("API9999E")
                .message("[error] customized message.").build();
        return super.handleExceptionInternal(e, error, headers, HttpStatus.BAD_REQUEST, request);
    }

}
