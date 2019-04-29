package com.eternal.web.config;

import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import com.eternal.web.json.deselializer.SortKeyTypeDesilializer;
import com.eternal.web.json.deselializer.SortTypeDesilializer;
import com.eternal.web.json.selializer.SortKeyTypeSelializer;
import com.eternal.web.json.selializer.SortTypeSelializer;
import com.eternal.web.message.MessageSourceImpl;
import com.eternal.web.type.SortKeyType;
import com.eternal.web.type.SortType;
import com.eternal.web.type.converter.SortKeyTypeConverter;
import com.eternal.web.type.converter.SortTypeConverter;
import com.fasterxml.jackson.annotation.JsonInclude;

@Configuration
public class WebApiConfig extends WebMvcConfigurationSupport {

    /** {@inheritDoc} */
    @Override
    public Validator getValidator() {
        return defaultValidator(new MessageSourceAutoConfiguration().messageSource(new MessageSourceProperties()));
    }

    /**
     * バリデーションメッセージを独自に設定する
     *
     * @param message {@link MessageSourceImpl}
     * @return {@link LocalValidatorFactoryBean}
     */
    @Bean
    public LocalValidatorFactoryBean defaultValidator(MessageSource message) {
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setValidationMessageSource(message);
        return factory;
    }

    /**
     * JacksonのObjectMapperをカスタマイズ
     *
     * @return {@link Jackson2ObjectMapperBuilder}
     */
    @Bean
    public Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                // nullの項目を出力しない
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                // 独自のEnumのSerializer
                .serializerByType(SortType.class, new SortTypeSelializer())
                .serializerByType(SortKeyType.class, new SortKeyTypeSelializer())
                // 独自のEnumのDesilializer
                .deserializerByType(SortType.class, new SortTypeDesilializer())
                .deserializerByType(SortKeyType.class, new SortKeyTypeDesilializer());
    }

    /** {@inheritDoc} */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SortKeyTypeConverter());
        registry.addConverter(new SortTypeConverter());
    }
}
