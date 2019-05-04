package com.eternal.web.config;

import com.eternal.web.json.deserializer.EvaluationTypeDeserializer;
import com.eternal.web.json.deserializer.SortKeyTypeDeserializer;
import com.eternal.web.json.deserializer.SortTypeDeserializer;
import com.eternal.web.json.selializer.EvaluationTypeSerializer;
import com.eternal.web.json.selializer.SortKeyTypeSerializer;
import com.eternal.web.json.selializer.SortTypeSerializer;
import com.eternal.web.message.MessageSourceImpl;
import com.eternal.web.type.EvaluationType;
import com.eternal.web.type.SortKeyType;
import com.eternal.web.type.SortType;
import com.eternal.web.type.converter.EvaluationTypeConverter;
import com.eternal.web.type.converter.SortKeyTypeConverter;
import com.eternal.web.type.converter.SortTypeConverter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;


@SpringBootConfiguration
public class WebApiConfig {

    /** {@link MessageSource}に関する設定 */
    @SpringBootConfiguration
    static class MessageConfig {

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
    }

    /** MVCに関する設定 */
    @SpringBootConfiguration
    @RequiredArgsConstructor
    static class MvcConfig extends WebMvcConfigurationSupport {

        /** {@link ObjectMapper } */
        private final ObjectMapper mapper;

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

        /** {@inheritDoc} */
        @Override
        public void addFormatters(FormatterRegistry registry) {
            // enumのカスタムコンバーター
            registry.addConverter(new SortKeyTypeConverter());
            registry.addConverter(new SortTypeConverter());
            registry.addConverter(new EvaluationTypeConverter());
        }

        /** {@inheritDoc}*/
        @Override
        public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            converters.add(new MappingJackson2HttpMessageConverter(mapper));
        }
    }

    /** {@link ObjectMapper} に関する設定 */
    @SpringBootConfiguration
    static class JacksonConfig {

        /**
         * Jacksonの{@link ObjectMapper}をカスタマイズ
         *
         * @return {@link Jackson2ObjectMapperBuilder}
         */
        @Bean
        public Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder() {
            return new Jackson2ObjectMapperBuilder()
                    // nullの項目を出力しない
                    .serializationInclusion(JsonInclude.Include.NON_ABSENT)
                    // 独自のEnumのSerializer
                    .serializerByType(SortType.class, new SortTypeSerializer())
                    .serializerByType(SortKeyType.class, new SortKeyTypeSerializer())
                    .serializerByType(EvaluationType.class, new EvaluationTypeSerializer())
                    // 独自のEnumのDeserializer
                    .deserializerByType(SortType.class, new SortTypeDeserializer())
                    .deserializerByType(SortKeyType.class, new SortKeyTypeDeserializer())
                    .deserializerByType(EvaluationType.class, new EvaluationTypeDeserializer());
        }
    }
}
