package com.eternal.web.message;

/**
 * MessageCode
 *
 * @author taiki0304
 */
public abstract class MessageCode {

    /** リクエストの引数が不正です */
    public static final String typeMismatchOthers = "typeMismatch.others";
    /** [field={0}]引数が不正です */
    public static final String typeMismatchFields = "typeMismatch.fields";
    /** システムエラーが発生しました */
    public static final String exception = "error.exception";

    /** ログインに失敗しました */
    public static final String securityLogin = "error.security.login";
    /** ログイン状態が有効ではありません */
    public static final String securityAuthentication = "error.security.authentication";
    /** 情報が見つかりませんでした */
    public static final String securityNotFound = "error.security.entityNotFound";
    /** 対象機能の利用が認められていません */
    public static final String securityAccessDenied = "error.security.accessDenied";
}
