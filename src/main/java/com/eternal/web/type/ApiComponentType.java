package com.eternal.web.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ApiComponentType
 *
 * @author taiki0304
 *
 */
@AllArgsConstructor
@Getter
public enum ApiComponentType {

    /** Ref001_トークテーマ一覧取得 */
    REF001("ref001"),
    /** Ref002_トークテーマカテゴリ一覧取得 */
    REF002("ref002"),
    /** Ref003_コメント一覧取得 */
    REF003("ref003"),
    /** Ref004_検索結果取得 */
    REF004("ref004"),
    /** Upd001_トークテーマ投稿 */
    UPD001("upd001"),
    /** Upd002_トークテーマ評価 */
    UPD002("upd002"),
    /** Upd003_コメント投稿 */
    UPD003("upd003");

    /** API名 */
    private String name;
}
