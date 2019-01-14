package com.eternal.web.aop;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import com.eternal.web.logger.ApplicationLogger;
import com.eternal.web.logger.RequestLogger;
import com.eternal.web.logger.ResponseLogger;

/**
 * ApiLogAspect
 *
 * @author taiki0304
 *
 */
@Aspect
@Component
public class ApiLogAspect {

    /**
     * リクエストログアスペクト
     *
     * @param joinpoint
     * @param apiLog
     */
    @Before("@annotation(apiLog)")
    public void logRequest(JoinPoint joinpoint, ApiLog apiLog) {
        RequestLogger.log(apiLog.apiComponentType(), Arrays.stream(joinpoint.getArgs()));
    }

    /**
     * レスポンスログアスペクト
     *
     * @param joinpoint
     * @param apiLog
     * @param responseDto
     */
    @AfterReturning(value = "@annotation(apiLog)", returning = "responseDto")
    public void logResponse(JoinPoint joinpoint, ApiLog apiLog, Object responseDto) {
        ResponseLogger.log(apiLog.apiComponentType(), responseDto);
    }

    /**
     * アプリケーションログアスペクト
     * @param joinpoint
     * @param appLog
     * @throws Throwable
     */
    @Before("@annotation(appLog)")
    public void logApplication(JoinPoint joinpoint, AppLog appLog) throws Throwable {
        ApplicationLogger.log(joinpoint.getSignature(), Arrays.stream(joinpoint.getArgs()));
    }

}
