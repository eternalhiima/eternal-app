package com.eternal.web.message;

import java.util.Locale;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

/**
 * MessageSourceImpl
 *
 * @author taiki0304
 *
 */
public class MessageSourceImpl implements MessageSource {

    /** メッセージソース */
    private final MessageSource messageSource;

    public MessageSourceImpl() {
        messageSource = new MessageSourceAutoConfiguration().messageSource();
    }

    /**
     *
     * @param code コード
     * @return message
     */
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

    /**
     *
     * @param code コード
     * @param args 置換文字列
     * @return message
     */
    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, Locale.getDefault());
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return messageSource.getMessage(code, args, locale);
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return messageSource.getMessage(resolvable, locale);
    }
}
