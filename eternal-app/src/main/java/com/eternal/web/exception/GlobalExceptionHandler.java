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

@RestControllerAdvice
@RequiredArgsConstructor
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
        return super.handleExceptionInternal(e,
                createErrorInfoResponse(e.getErrorCode(), messageSource.getMessage(e.getErrorCode())), null,
                HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Springboot内の{#link @Valid}で定義している例外の処理
     *
     * @param e
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(e,
                createErrorInfoResponse(MessageCode.API9000E,
                        messageSource.getMessage(MessageCode.API9000E,
                                Arrays.asObjectArray(e.getBindingResult().getTarget()))),
                null, HttpStatus.INTERNAL_SERVER_ERROR, request);
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
                createErrorInfoResponse(MessageCode.API9999E, messageSource.getMessage(MessageCode.API9999E)), null,
                HttpStatus.INTERNAL_SERVER_ERROR, request);
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

    /**
     * createErrorInfoResponseを生成
     *
     * @param code エラーメッセージコード
     * @param message エラーメッセージ
     * @return ErrorInfoResponse
     */
    private ErrorInfoResponse createErrorInfoResponse(String code, String message) {
        return ErrorInfoResponse.builder()
                .code(code)
                .message(message).build();
    }
}
