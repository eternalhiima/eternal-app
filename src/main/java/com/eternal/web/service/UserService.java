package com.eternal.web.service;

import java.util.Arrays;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eternal.web.aop.AppLog;
import com.eternal.web.entity.User;
import com.eternal.web.exception.ServiceException;
import com.eternal.web.message.MessageCode;
import com.eternal.web.message.MessageSourceImpl;
import com.eternal.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;

/**
 * {@link UserService}
 *
 * @author taiki0304
 */
@Service
@RequiredArgsConstructor
public class UserService {

    /** {@link UserRepository} */
    private final UserRepository userRepository;

    /** {@link MessageSource} */
    private final MessageSourceImpl messageSource;

    /**
     * {@link User}を新規に追加する
     * すでにユーザー名が使用されている場合は{@link ServiceException}をthrowする
     *
     * @param userName
     * @return {@link User}
     * @throws {@link ServiceException}
     */
    @AppLog
    @Transactional
    public User addUser(String userName) {
        // ユーザー名がすでに使用されている場合はエラー
        userRepository.findByUserName(userName)
                .ifPresent(u -> throwUserDuplicateException(u.getUserName()));
        // ユーザーを新規作成
        return userRepository.saveAndFlush(User.of(userName));
    }

    /**
     * {@link User}をIdで検索する
     *
     * @param userId
     * @return {@link User}
     * @throws {@link ServiceException}
     */
    public User findById(Long userId) {
        return userRepository.findById(userId)
        .orElseThrow(() -> new ServiceException(MessageCode.UNKNOWN_USER,
                messageSource.getMessage(MessageCode.UNKNOWN_USER)));
    }

    private void throwUserDuplicateException(String userName) {
        throw new ServiceException(MessageCode.POST_DUPLICATE_USER,
                messageSource.getMessage(MessageCode.POST_DUPLICATE_USER, Arrays.asList(userName)));
    }
}
