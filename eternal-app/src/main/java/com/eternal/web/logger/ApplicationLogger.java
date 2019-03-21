package com.eternal.web.logger;

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
     *
     * @param signature シグネチャ
     */
    public static void log(Signature signature) {
        String appLog = "class: " + signature.getDeclaringType().toString();
        log.info(appLog);
    }

}
