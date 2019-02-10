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

    /** {0}の投稿に成功しました。 */
    public static final String postTalkSuccess = "service.upd001.post.success";
    /** {0}の投稿に失敗しました。もう一度トークテーマを投稿して下さい。 */
    public static final String postTalkFailure ="service.upd001.post.failure";
}
