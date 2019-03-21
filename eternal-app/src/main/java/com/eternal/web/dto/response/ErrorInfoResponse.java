package com.eternal.web.dto.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ErrorInfoResponse implements Serializable {

    /** メッセージコード */
    private final String code;

    /** エラーメッセージ */
    private final String message;
}
