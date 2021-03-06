package com.eternal.web.exception;

import lombok.Getter;

/**
 * 業務例外
 *
 * @author taiki0304
 *
 */
@Getter
public class ServiceException extends RuntimeException {

    /** エラーコード */
    private final String errorCode;

    /** エラーメッセージ */
    private final String message;

    public ServiceException(String errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }
}
