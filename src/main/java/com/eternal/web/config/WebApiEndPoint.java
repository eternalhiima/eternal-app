package com.eternal.web.config;

public abstract class WebApiEndPoint {

    /** ルートエンドポイント */
    public static final String ROOT_END_POINT = "/hiima/api/internal/v1";

    /** Ref001_トークテーマ一覧取得 */
    public static final String TALKLIST_END_POINT = "/talkList";

    /** Ref002_トークテーマカテゴリ一覧取得 */
    public static final String CATEGORYLIST_END_POINT = "/categoryList";

    /** Ref003_コメント一覧取得 */
    public static final String COMMENTIST_END_POINT = "/commentList";

    /** Ref004_検索結果取得 */
    public static final String SEARCH_END_POINT = "/search";

    /** Upd001_トークテーマ投稿 */
    public static final String POSTTALK_END_POINT = "/postTalk";

    /** Upd002_トークテーマ評価 */
    public static final String EVALUATE_END_POINT = "/evaluate";

    /** Upd003_コメント投稿 */
    public static final String POSTCOMMENT_END_POINT = "/postComment";
}
