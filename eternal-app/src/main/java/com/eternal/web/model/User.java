/**
 *
 */
package com.eternal.web.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * User
 *
 * @author taiki0304
 */
@Data
public class User {

    /** ユーザーID */
    private BigDecimal userId;

    /** ユーザー名 */
    private String userName;

    /** サムネイルURL */
    private String thumbnailUrl;

    /** 登録日時 */
    private LocalDateTime createDateTime;
}
