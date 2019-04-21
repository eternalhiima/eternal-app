package com.eternal.web.dto.response;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ErrorInfoResponse implements Serializable {

    /** エラーリスト */
    private List<ErrorInfo> errorInfoList;

    @Data
    public static class ErrorInfo {
        /** メッセージコード */
        private final String code;

        /** エラーメッセージ */
        private final String message;
    }
}
