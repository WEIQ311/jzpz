package com.jzpz.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * Created by weiQiang on 2016/10/2.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    //配置Spring Security的Filter链
    @Override
    public void configure(WebSecurity web) throws Exception {

    }
    //配置如何通过拦截器保护请求
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(
                    HttpMethod.GET,
                    "/login.html",
                    "/*.js",
                    "/**/*.js",
                    "/**/*.woff2",
                    "/**/*.woff",
                    "/**/*.map",
                    "/**/*.css",
                    "/**/*.TTF"
            )
            .permitAll()
            .antMatchers(
                    HttpMethod.POST,
                    "/user/*"
            )
            .permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login.html")
            .defaultSuccessUrl("/index.html")
            .permitAll()
            .and()
            .rememberMe()
            .tokenValiditySeconds(1000)
            .key("spitterKey")
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            //注销失败跳转到登录页面
            .logoutSuccessUrl("/login.html")
            .permitAll();
        // 允许iframe 嵌套
        http.headers().frameOptions().disable();
       // http.headers().disable();
    }
    //配置user-detail
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("admin").password("bonc").roles("USER","ADMIN");
        auth.userDetailsService(userDetailsService);//.passwordEncoder(passwordEncoder());
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }
}