package com.eternal.web.logger;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.eternal.web.type.ApiComponentType;

/**
 * APIレスポンスログ
 *
 * @author taiki0304
 *
 */
public class ResponseLogger {

    /** レスポンスロガー */
    private static final Logger log = LoggerFactory.getLogger("com.eternal.web.logger.ResponseLogger");

    /**
     * レスポンスログ
     *
     * @param apiComponentType APIコンポーネントタイプ
     * @param responseDto レスポンスDto
     */
    public static void log(ApiComponentType apiComponentType, Object responseDto) {
        String apiName = "apiName: " + apiComponentType.getName();
        if (Objects.isNull(responseDto)) {
            log.info(apiName + ", Response is null.");
            return;
        }
        StringJoiner responseBody = new StringJoiner(", ", ", responseBody: ", "");
        Arrays.stream(responseDto.getClass().getDeclaredFields()).forEach(field -> {
            try {
                field.setAccessible(true);
                responseBody.add(field.getName() + " = " + field.get(responseDto));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        log.info(apiName + responseBody.toString());
    }

}
