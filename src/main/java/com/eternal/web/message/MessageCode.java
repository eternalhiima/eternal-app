package com.eternal.web.message;

/**
 * MessageCode
 *
 * @author taiki0304
 */
public abstract class MessageCode {

    /** {トークテーマタイトル}の投稿に成功しました。 */
    public static final String POST_TALK_SUCCESS = "API10001";
    /** {トークテーマタイトル}の投稿に失敗しました。もう一度トークテーマを投稿して下さい。 */
    public static final String POST_TALK_FAILURE ="API10002";
    /** {ユーザー}はすでに使用されています。 */
    public static final String POST_DUPLICATE_USER = "API10003";
    /** 指定したカテゴリは存在しません。 */
    public static final String UNKNOWN_CATEGORY = "API10004";

    /** 単項目チェックエラー */
    public static final String VALIDATE_EXCEPTION = "API90000";
    /** システムエラーが発生しました */
    public static final String EXCEPTION = "API99999";
}
