package com.eternal.web.message;

/**
 * MessageCode
 *
 * @author taiki0304
 */
public abstract class MessageCode {

    /** リクエストの引数が不正です */
    public static final String TYPE_MISMATCH_OTHERS = "typeMismatch.others";
    /** [field={0}]引数が不正です */
    public static final String TYPW_MISMATCH_FIELDS = "typeMismatch.fields";
    /** システムエラーが発生しました */
    public static final String EXCEPTION = "error.exception";

    /** {トークテーマタイトル}の投稿に成功しました。 */
    public static final String POST_TALK_SUCCESS = "service.upd001.post.success";
    /** {トークテーマタイトル}の投稿に失敗しました。もう一度トークテーマを投稿して下さい。 */
    public static final String POST_TALK_FAILURE ="service.upd001.post.failure";
    /** {ユーザー}はすでに使用されています。 */
    public static final String POST_DUPLICATE_USER = "service.upd001.post.userDuplicate";
    /** 指定したカテゴリは存在しません。 */
    public static final String UNKNOWN_CATEGORY = "service.ref001.get.unknownCategory";
}
