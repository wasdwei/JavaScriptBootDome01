//package com.example.javascriptbootdome01.Security.JDBC;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
///**
// * @Name: wql
// * @Time: 2023-10-14  11:44
// */
//@Controller
//public class SecurityFileController {
//    private String TAG = "FilmeController";
//    //影片详情页
//    @GetMapping("/detail/{type}/{path}")
//    public String toDetail(@PathVariable("type") String type, @PathVariable("path") String path) {
//        String value = "detail/" + type + "/" + path;
//        System.out.println(TAG + "===toDeil===" + value);
//        return value;
//    }
//    @GetMapping("/userLogin")
//    public String toLoginPage(){
//        return "detail/login";
//    }
//
//    @GetMapping("/loginError")
//    String loginerror(){
//        return "login/loginerror";
//    }
//
//    @GetMapping("/index2")
//    String index2(){
//        return "login/index2";
//    }
//
//}
