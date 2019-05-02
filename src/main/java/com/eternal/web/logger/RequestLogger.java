package com.eternal.web.logger;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.eternal.web.type.ApiComponentType;
import org.springframework.stereotype.Component;

/**
 * APIリクエストログ
 *
 * @author taiki0304
 */
@Component
@RequiredArgsConstructor
public class RequestLogger {

    /** リクエストロガ- */
    private static final Logger log = LoggerFactory.getLogger("com.eternal.web.logger.RequestLogger");

    /** {@link ObjectMapper} */
    private final ObjectMapper mapper;

    /**
     * リクエストログを出力する
     *
     * @param {@link ApiComponentType}
     * @param args   リクエストDTO
     */
    public void log(ApiComponentType apiComponentType, Object[] args) {
        String requestLog = "apiName:" + apiComponentType.getName() + ","
                + Stream.of(args).map(a -> {
            try {
                return mapper.writeValueAsString(a);
            } catch (JsonProcessingException e) {
                return StringUtils.EMPTY;
            }
        }).collect(Collectors.joining(", ", args[0].getClass().getSimpleName() + ":[param:", "]"));
        log.info(requestLog);
    }
}
