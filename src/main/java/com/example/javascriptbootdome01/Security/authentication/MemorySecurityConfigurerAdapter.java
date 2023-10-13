//package com.example.javascriptbootdome01.Security.authentication;
//
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
///**
// * @Name: wql
// * @Time: 2023-10-08  11:41
// */
//@EnableWebSecurity
//public class MemorySecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        auth.inMemoryAuthentication().passwordEncoder(encoder).withUser("李四")
//                .password(encoder.encode("123456")).roles("common").and()
//                .withUser("张三").password(encoder.encode("123456")).roles("vip");
//    }
//}
