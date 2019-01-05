package com.eternal.web.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        ErrorInfoResponse error;
        if (e instanceof EternalException) {
            String code = ((EternalException) e).getErrorCode();
            error = ErrorInfoResponse.builder().
                    code(code)
                    .message(messageSource.getMessage(code)).build();
        }

        // TODO: 想定される例外クラスを記述する
        error = ErrorInfoResponse.builder()
                .code(MessageCode.API9999E)
                .message(messageSource.getMessage(MessageCode.API9999E)).build();

        log.error("[error]" + error.toString());
        return super.handleExceptionInternal(e, error, headers, HttpStatus.BAD_REQUEST, request);
    }
}
