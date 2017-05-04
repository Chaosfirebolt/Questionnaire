package com.questionnaire.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by ChaosFire on 13.4.2017 г
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final int ONE_WEEK_IN_SECONDS = 604_800;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/", "/register/**", "/ranking/**", "/bootstrap-jquery/**", "/custom/**", "/connect/**").permitAll()
                .antMatchers("/register", "/login").anonymous()
                .antMatchers("/questions/actions", "/questions/actions/**").access("hasRole('GOD')")
                .antMatchers("/questions/**").access("hasRole('GOD') or hasRole('ADMIN')")
                .antMatchers("/authority/**").access("hasRole('GOD')")
                .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login").permitAll()
                    .usernameParameter("username").passwordParameter("password")
                .and()
                    .rememberMe()
                    .rememberMeParameter("rememberMe")
                    .key("kjasdjfhkldsldfkdvnmerkl")
                    .rememberMeCookieName("QuestionnaireRememberMeCookie")
                    .tokenValiditySeconds(ONE_WEEK_IN_SECONDS)
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
                    .permitAll()
                .and()
                    .csrf().csrfTokenRepository(csrfTokenRepository());
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository tokenRepository = new HttpSessionCsrfTokenRepository();
        final String attributeName = "_csrf";
        tokenRepository.setSessionAttributeName(attributeName);
        return tokenRepository;
    }
}