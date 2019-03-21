package com.eternal.web.logger;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.eternal.web.type.ApiComponentType;

/**
 * APIリクエストログ
 *
 * @author taiki0304
 *
 */
public class RequestLogger {

    /** リクエストロガー */
    private static final Logger log = LoggerFactory.getLogger("com.eternal.web.logger.RequestLogger");

    /**
     * リクエストログ
     *
     * @param apiComponentType APIコンポーネントタイプ
     * @param args リクエストパラメータ
     */
    public static void log(ApiComponentType apiComponentType, Stream<Object> args) {
        String requestLog = "apiName: " + apiComponentType.getName() + ", requestParams: "
                + args.map(arg -> arg.toString()).collect(Collectors.joining(", ", "[param: ", "]"));
        log.info(requestLog);
    }

}
