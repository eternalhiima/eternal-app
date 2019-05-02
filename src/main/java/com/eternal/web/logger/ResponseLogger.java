package com.eternal.web.logger;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.eternal.web.type.ApiComponentType;
import org.springframework.stereotype.Component;

/**
 * APIレスポンスログ
 *
 * @author taiki0304
 */
@RequiredArgsConstructor
@Component
public class ResponseLogger {

    /** レスポンスロガー */
    private static final Logger log = LoggerFactory.getLogger("com.eternal.web.logger.ResponseLogger");

    /** {@link ObjectMapper} */
    private final ObjectMapper mapper;

    /**
     * レスポンスログ
     *
     * @param {@link ApiComponentType}
     * @param response レスポンス
     */
    public void log(ApiComponentType apiComponentType, Object response) {
        StringJoiner responseLog = new StringJoiner(",");
        responseLog.add("apiName:" + apiComponentType.getName());
        try {
            responseLog.add(response.getClass().getSimpleName() + ":[" + mapper.writeValueAsString(response) + "]");
        } catch (JsonProcessingException e) {
            // nothing to do
        }
        log.info(responseLog.toString());
    }
}
