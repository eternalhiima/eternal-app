package com.eternal.web.logger;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * APIアプリケーションログ
 *
 * @author taiki0304
 *
 */
public class ApplicationLogger {

    /** アプリケーションロガー */
    private static final Logger log = LoggerFactory.getLogger("com.eternal.web.logger.ApplicationLogger");

    /**
     * アプリケーションログ
     * @param stream
     * @param apiComponentType APIコンポーネントタイプ
     * @param responseDto アプリケーションDto
     */
    public static void log(Signature signature, Stream<Object> args) {
        String appLog = "class: " + signature.getDeclaringType().toString() + "params: " + args.map(arg -> arg.toString()).collect(Collectors.joining(", ", "[param: ", "]"));
        log.info(appLog);
    }

}
