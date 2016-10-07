package com.jzpz.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by weiQiang on 2016/10/2.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //配置Spring Security的Filter链
    @Override
    public void configure(WebSecurity web) throws Exception {

    }
    //配置如何通过拦截器保护请求
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(
                    HttpMethod.GET,
                    "/*.html",
                    "/**/*.html",
                    "/*.js",
                    "/**/*.js",
                    "/**/*.woff2",
                    "/**/*.woff",
                    "/**/*.map",
                    "/**/*.css",
                    "/**/*.TTF"
            ).permitAll()
            .antMatchers("/user/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login.html")
            .permitAll()
            .and()
            .rememberMe()
            .tokenValiditySeconds(1000)
            .key("spitterKey")
            .and()
            .logout()
            .permitAll();
        http.headers().disable();
    }
    //配置user-detail
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("admin").password("bonc").roles("USER_ROLE");
        //auth.userDetailsService(null);
    }
}