package com.eternal.web.aop;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import com.eternal.web.logger.RequestLogger;
import com.eternal.web.logger.ResponseLogger;

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

    @AfterReturning(value ="@annotation(apiLog)", returning="responseDto")
    public void logResponse(JoinPoint joinpoint, ApiLog apiLog, Object responseDto) {
        ResponseLogger.log(apiLog.apiComponentType(), responseDto);
    }
    // アプリケーションログ

}
