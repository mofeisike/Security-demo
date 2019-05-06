package com.mofei.securitythyboot.security;

import com.mofei.securitythyboot.encoder.MyPasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@Configuration
/*注解必须导入Security包*/
//@EnableWebSecurity
/*启动Spring Security的过滤器链*/
public class SpringSecurityconfig_httpBasic extends WebSecurityConfigurerAdapter{

    //该方法的作用就是代之前配置：<security:authentication-manager>
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
        inMemoryAuthentication : 内存身份验证(比如密码固定)
        withUser("user") : 添加用户
        password("123") : 添加密码
        authorities("PRODUCT_ADD") : 添加用户权限
        .passwordEncoder(new MyPasswordEncoder()): 页面提交时候，密码以明文的方式进行匹配。
        */
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
                .withUser("user").password("123").authorities("PRODUCT_ADD");
    }

    //该方法的作用就是代之前配置：<secrity:http>
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        authorizeRequests : 发出一个拦截请求
        antMatchers : 拦截的表达式(链接)(/** : 拦截所有)
        fullyAuthenticated : 认证的拦截
        httpBasic : 使用httpBasic 的方式登录
        */
        http.authorizeRequests()
                .antMatchers("/**")
                .fullyAuthenticated()
                .and()
                .httpBasic();
    }
}