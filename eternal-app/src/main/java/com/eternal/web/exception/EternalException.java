package com.eternal.web.exception;

import lombok.Getter;

/**
 * 業務例外クラス
 *
 * @author taiki0304
 *
 */
@Getter
public class EternalException extends Exception {

    /** エラーコード */
    private String errorCode;

    public EternalException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }
}
