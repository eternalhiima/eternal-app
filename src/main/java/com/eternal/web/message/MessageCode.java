package com.eternal.web.message;

/**
 * MessageCode
 *
 * @author taiki0304
 */
public abstract class MessageCode {

    /** {0}の投稿に成功しました。 */
    public static final String POST_SUCCESS = "API10001";
    /** {0}の投稿に失敗しました。  */
    public static final String POST_FAILURE ="API10002";
    /** {ユーザー}はすでに使用されています。 */
    public static final String POST_DUPLICATE_USER = "API10003";
    /** 指定したカテゴリは存在しません。 */
    public static final String UNKNOWN_CATEGORY = "API10004";
    /** 指定したトークテーマは存在しません。 */
    public static final String UNKNOWN_TALK = "API10005";
    /** 指定したユーザーは存在しません。 */
    public static final String UNKNOWN_USER = "API10006";

    /** 単項目チェックエラー */
    public static final String VALIDATE_EXCEPTION = "API90000";
    /** {0}の型が不正です。 */
    public static final String TYPE_MISMATCH_EXCEPTION = "API91000";

    /** システムエラーが発生しました */
    public static final String EXCEPTION = "API99999";
}
