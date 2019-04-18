package com.eternal.web.config;

import java.nio.charset.StandardCharsets;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import com.eternal.web.json.deselializer.SortTypeDesilializer;
import com.eternal.web.json.selializer.SortTypeSelializer;
import com.eternal.web.message.MessageSourceImpl;
import com.eternal.web.type.SortType;
import com.fasterxml.jackson.annotation.JsonInclude;

@Configuration
@EnableWebMvc
public class WebApiConfig extends WebMvcConfigurationSupport {

    /**
     * {@link MessageSource}を独自に拡張する
     *
     * @return {@link MessageSourceImpl}
     */
    @Bean("messageSource")
    public MessageSourceImpl messageSource() {
        MessageSourceProperties prop = new MessageSourceProperties();
        return new MessageSourceImpl(prop);
    }

    /** {@inheritDoc} */
    @Override
    public Validator getValidator() {
        MessageSourceProperties prop = new MessageSourceProperties();
        prop.setBasename("classpath:/i18n/ValidationMessages");
        prop.setEncoding(StandardCharsets.UTF_8);
        MessageSource messageSource = new MessageSourceAutoConfiguration().messageSource(prop);
        return defaultValidator(messageSource);
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
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        // nullの項目を出力しない
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        // 独自のEnumのSerializer
        builder.serializerByType(SortType.class, new SortTypeSelializer());

        // 独自のEnumのDesilializer
        builder.deserializerByType(SortType.class, new SortTypeDesilializer());
        return builder;
    }
}
