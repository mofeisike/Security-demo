package com.mofei.securitytest1.config;

import com.mofei.securitytest1.encoder.MyPasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    //定制授权的请求规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll() //允许一切请求
                .antMatchers("/level1/**") //请求路径
                .hasAnyAuthority("VIP1") //给请求路径添加名称
                .antMatchers("/level2/**").hasAnyAuthority("VIP2")
                .antMatchers("/level3/**").hasAnyAuthority("VIP3");

        //自定义登录
        http.formLogin()
                .usernameParameter("user") //自定义表单的name参数
                .passwordParameter("pwd") //自定义表单的password的参数
                .loginPage("/userlogin") //自定义login页面的路径
                .failureUrl("/login?error") //自定义失败页面
                .loginProcessingUrl("/userlogin"); // 自定义表单的地址action,默认是.loginPage("/userlogin")
        // 默认post形式的/Login代表处理登录(在表单使用action="/login")
        // 一但定制LoginPage；那么 loginPage的post请求就是登陆


        //开启自动配置的注销功能。
        //1、访间/Logout表示用户注销，清空sesston
        http.logout()
                .logoutSuccessUrl("/"); //注销成功后,来到首页
        /*
        <a href="/logout">退出登录</a>
        Spring Security4 默认是开启注销的,点击链接就可以,
        logout() 就是配置信息
        */

        //开启记住我的功能
        http.rememberMe()
                .rememberMeParameter("remeber"); //自定义remeber的参数
        //登陆成功以后，将cookic发给浏览器保存，以后访问页面带上这个cookic，只要通过检查就可以免登录
        //点击注销会删除cookie

    }

    //定制认证的请求规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //默认存在到内存
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
        .withUser("user").password("123").authorities("VIP1","VIP2")
        .and()
        .withUser("lisi").password("123").authorities("VIP2","VIP3")
        .and()
        .withUser("wangwu").password("123").authorities("VIP1","VIP3");
    }
    /*
     authorities("VIP1","VIP2") Springboot 2.0
     roles("VIP1","VIP2")  Springboot 1.5
    */
}
