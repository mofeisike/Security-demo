package com.mofei.securitythyboot.security;

import com.mofei.securitythyboot.encoder.MyPasswordEncoder;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityconfig_formLogin extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // authorities 指定权限, 权限名称在
        // .antMatchers("/product/add").hasAnyAuthority("PRODUCT_ADD")
        // 在这里已经确定,链接和权限名称
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
                .withUser("user").password("123").authorities("PRODUCT_ADD","PRODUCT_LIST");
        /*权限的定制, 只有PRODUCT_ADD,PRODUCT_LIST可以访问*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // permitAll(); // 这些页面不需要身份认证,其他请求需要认证
        // csrf().disable(); // 禁用跨站攻击
        // .formLogin() // 表单登录  来身份认证
        // .loginPage("login") // 自定义登录页面
        http.authorizeRequests()
                .antMatchers("/product/add").hasAnyAuthority("PRODUCT_ADD")
                .antMatchers("/product/list").hasAnyAuthority("PRODUCT_LIST")
                .antMatchers("/product/update").hasAnyAuthority("PRODUCT_UPDATE")
                .antMatchers("/product/delete").hasAnyAuthority("PRODUCT_DELETE")
                .antMatchers("/login").permitAll()
                .antMatchers("/**")
                .fullyAuthenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and().csrf().disable()
        ;
    }
}