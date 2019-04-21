package com.eternal.web.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.eternal.web.type.ApiComponentType;

/**
 * ApiLogを出力する対象に付加する
 * API_REQUEST.logとAPI_RESPONSE.logの出力対象となる
 *
 * @author taiki0304
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiLog {

    /** APIコンポーネントタイプ */
    ApiComponentType apiComponentType();

}
