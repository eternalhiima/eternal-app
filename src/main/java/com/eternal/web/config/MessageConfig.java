package com.eternal.web.config;

import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.eternal.web.message.MessageSourceImpl;

@Configuration
public class MessageConfig {

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
