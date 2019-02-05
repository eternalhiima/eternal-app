/**
 *
 */
package com.eternal.web.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import com.eternal.web.message.MessageCode;
import com.eternal.web.message.MessageSourceImpl;
import lombok.RequiredArgsConstructor;

/**
 * WebApiAuthenticationEntryPoint
 *
 * @author taiki0304
 */
@RequiredArgsConstructor
public class WebApiAuthenticationEntryPoint implements AuthenticationEntryPoint {


    /** メッセージソース */
    private final MessageSourceImpl mssageSource;

    /**
     * {@inheritDoc}
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        if (response.isCommitted()) {
            return;
        }
        if (authException instanceof InsufficientAuthenticationException) {
            writeReponseEmpty(response, HttpServletResponse.SC_FORBIDDEN, mssageSource.getMessage(MessageCode.securityAccessDenied));
        } else {
            writeReponseEmpty(response, HttpServletResponse.SC_UNAUTHORIZED, mssageSource.getMessage(MessageCode.securityAuthentication));
        }
    }

    private void writeReponseEmpty(HttpServletResponse response, int status, String message) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\": \"" + message + "\"}");
    }

}
