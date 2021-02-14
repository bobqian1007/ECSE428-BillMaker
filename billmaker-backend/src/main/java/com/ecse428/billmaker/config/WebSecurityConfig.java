package com.ecse428.billmaker.config;



import com.ecse428.billmaker.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.ecse428.billmaker.service.UserDetailsServiceImpl;


@Configuration @EnableWebSecurity public class WebSecurityConfig
    extends WebSecurityConfigurerAdapter {

    // Instantiate UserDetailsServiceImpl so we can use it
    @Autowired private UserDetailsServiceImpl userDetailsService;

    @Override protected void configure(HttpSecurity http) throws Exception {
        http
            // AUTHORIZE
            .authorizeRequests()
            .mvcMatchers("/").permitAll()
            //.mvcMatchers("/user/**").hasRole("USER")
            //.mvcMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated().and()
            // EXCEPTION
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
            .accessDeniedHandler(accessDeniedHandler()).and()
            // LOGIN
            .formLogin()
            .loginProcessingUrl("/login")
            .permitAll()
            .usernameParameter("email")
            .passwordParameter("password")
            .successHandler(authenticationSuccessHandler())
            .failureHandler(authenticationFailureHandler()).and()
            // LOGOUT
            .logout()
            .logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID")
            .logoutSuccessHandler(logoutSuccessHandler())
            //.addLogoutHandler(new CookieClearingLogoutHandler())
            .and()
            // CSRF
            .csrf().disable()
        //.ignoringAntMatchers("/login")
        //.csrfTokenRepository(new CookieCsrfTokenRepository())
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEnconderTest();
    }
    /*
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    AuthenticationEntryPoint authenticationEntryPoint() {
        return new SimpleAuthenticationEntryPoint();
    }

    AccessDeniedHandler accessDeniedHandler() {
        return new SimpleAccessDeniedHandler();
    }

    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleAuthenticationSuccessHandler();
    }

    AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleAuthenticationFailureHandler();
    }

    LogoutSuccessHandler logoutSuccessHandler() {
        return new HttpStatusReturningLogoutSuccessHandler();
    }

}
