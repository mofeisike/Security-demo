package com.mofei.securitytest1.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;


public class MyPasswordEncoder implements PasswordEncoder {

    /*
    Spring boot 2.0.1引用的security 依赖是 spring security 5.X版本，此版本需要提供一个PasswordEncorder的实例，
    否则后台汇报错误：
    java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
    并且页面毫无响应。
    因此，需要创建PasswordEncorder的实现类。
    */


    //此类使用了最简单的明文密码作为测试。
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}