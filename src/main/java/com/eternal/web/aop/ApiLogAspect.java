package com.eternal.web.aop;

import java.util.Arrays;
import java.util.stream.Stream;

import com.eternal.web.type.ApiComponentType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.eternal.web.logger.ApplicationLogger;
import com.eternal.web.logger.RequestLogger;
import com.eternal.web.logger.ResponseLogger;

/**
 * ApiLogAspect
 *
 * @author taiki0304
 */
@Aspect
@Component
@RequiredArgsConstructor
public class ApiLogAspect {

    /** {@link RequestLogger} */
    private final RequestLogger requestLogger;

    /** {@link ResponseLogger} */
    private final ResponseLogger responseLogger;

    /** {@link ApplicationLogger} */
    private final ApplicationLogger applicationLogger;

    /**
     * リクエストログアスペクト
     *
     * @param {@link JoinPoint} joinpoint
     * @param {@link ApiLog} apiLog
     */
    @Before("@annotation(apiLog)")
    public void logRequest(JoinPoint joinpoint, ApiLog apiLog) {
        requestLogger.log(apiLog.apiComponentType(), joinpoint.getArgs());
    }

    /**
     * レスポンスログアスペクト
     *
     * @param {@link JoinPoint} joinpoint
     * @param {@link ApiLog} apiLog
     * @param response
     */
    @AfterReturning(value = "@annotation(apiLog)", returning = "response")
    public void logResponse(JoinPoint joinpoint, ApiLog apiLog, Object response) {
        responseLogger.log(apiLog.apiComponentType(), response);
    }

    /**
     * エラーレスポンスログアスペク
     * @param response
     */
    @AfterReturning(pointcut = "execution(* com.eternal.web.exception.GlobalExceptionHandler.handle*(..))", returning = "response")
    public void logErrorResponse(Object response) {
        if (response instanceof ResponseEntity) {
            responseLogger.log(ApiComponentType.UNKNOWN, ((ResponseEntity) response).getBody());
        }
    }

    /**
     * アプリケーションログアスペクト
     *
     * @param {@link JoinPoint} joinpoint
     * @param {@link AppLog} appLog
     */
    @Before("@annotation(appLog)")
    public void logApplication(JoinPoint joinpoint, AppLog appLog) {
        applicationLogger.log(joinpoint.getStaticPart().toShortString(), joinpoint.getArgs());
    }

}
