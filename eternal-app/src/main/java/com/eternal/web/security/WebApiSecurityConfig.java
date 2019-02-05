/**
 *
 */
package com.eternal.web.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * WebApiSecurityConfig
 *
 * @author taiki0304
 */
@EnableWebSecurity
public class WebApiSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
     * {@inheritDoc}
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // アクセス権限の設定
                .anyRequest().authenticated().and()
                // ログイン処理の設定
                .formLogin()
                // ログイン処理のURL
                .loginPage("/login")
                // usernameのパラメタ名
                .usernameParameter("username")
                // passwordのパラメタ名
                .passwordParameter("password").permitAll().and()
                // ログアウト処理の設定
                .logout()
                // ログアウト処理のURL
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // ログアウト成功時の遷移先URL
                .logoutSuccessUrl("/login")
                // ログアウト時に削除するクッキー名
                .deleteCookies("JSESSIONID")
                // ログアウト時のセッション破棄を有効化
                .invalidateHttpSession(true).permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO インメモリDBと独自ユーザテーブルで処理が変わる部分
    }


}
