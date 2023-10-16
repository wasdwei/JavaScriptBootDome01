package com.example.javascriptbootdome01.Security.base;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

//package com.example.javascriptbootdome01.Security.base;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
///**
// * @Name: wql
// * @Time: 2023-10-07  15:54
// */
//@Controller
//public class FilmeController {
//    private String TAG = "FilmeController";
//    @GetMapping("/detail/{type}/{path}")
//    public String toDetail(@PathVariable("type") String type, @PathVariable("path") String path) {
//        String value = "detail/" + type + "/" + path;
//        System.out.println(TAG + "===toDeil===" + value);
//        return value;
//    }
//}
@Controller
public class FilmeController {
    private String TAG = "FilmeController";
    //影片详情页
    @GetMapping("/detail/{type}/{path}")
    public String toDetail(@PathVariable("type") String type, @PathVariable("path") String path) {
        String value = "detail/" + type + "/" + path;
        System.out.println(TAG + "===toDeil===" + value);
        return value;
    }
    @GetMapping("/userLogin")
    public String toLoginPage(){
        return "detail/login";
    }

    @GetMapping("/loginError")
    String loginerror(){
        return "login/loginerror";
    }

    @GetMapping("/index2")
    String index2(){
        return "login/index2";
    }
    @GetMapping("/getuserByContext")
    @ResponseBody
    public void getUser2() {
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println("userDetails: "+context);
        Authentication authentication = context.getAuthentication();
        UserDetails principal = (UserDetails)authentication.getPrincipal();
        System.out.println(principal);
        System.out.println("username: "+principal.getUsername());
    }

}

