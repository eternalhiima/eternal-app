/**
 *
 */
package com.eternal.web.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author taiki0304
 *
 */
public class WebApiLoginHandler
        implements AuthenticationSuccessHandler, AuthenticationFailureHandler, LogoutSuccessHandler {

    /**
     * ログイン成功処理
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // TODO: ログイン成功時の実装
        if (response.isCommitted()) {
            return;
        }
        writeReponseEmpty(response, HttpServletResponse.SC_OK);

    }

    /**
     * ログイン失敗処理
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        if (response.isCommitted()) {
            return;
        }
        writeReponseEmpty(response, HttpServletResponse.SC_BAD_REQUEST);

    }

    /*
     * ログアウト成功処理
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        if (response.isCommitted()) {
            return;
        }
        writeReponseEmpty(response, HttpServletResponse.SC_OK);

    }

    private void writeReponseEmpty(HttpServletResponse response, int status) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status);
        response.getWriter().write("{}");
    }

}
