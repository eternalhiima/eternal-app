package com.eternal.web.logger;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

/**
 * APIアプリケーションログ
 *
 * @author taiki0304
 */
@Component
@RequiredArgsConstructor
public class ApplicationLogger {

    /** アプリケーションロガー */
    private static final Logger log = LoggerFactory.getLogger("com.eternal.web.logger.ApplicationLogger");

    /** {@link ObjectMapper} */
    private final ObjectMapper mapper;

    /**
     * アプリケーションログ
     * @param method
     * @param args
     */
    public void log(String method, Object[] args) {
        String appLog = method + "," +
                Stream.of(args).map(a -> {
                    try {
                        return a.getClass().getSimpleName() + mapper.writeValueAsString(a);
                    } catch (JsonProcessingException e) {
                        return a.getClass().getSimpleName();
                    }
                }).collect(Collectors.joining(",", "param:[", "]"));
        log.info(appLog);
    }
}
