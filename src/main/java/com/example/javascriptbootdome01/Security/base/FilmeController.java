package com.example.javascriptbootdome01.Security.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Name: wql
 * @Time: 2023-10-07  15:54
 */
@Controller
public class FilmeController {
    private String TAG = "FilmeController";
    @GetMapping("/detail/{type}/{path}")
    public String toDetail(@PathVariable("type") String type, @PathVariable("path") String path) {
        String value = "detail/" + type + "/" + path;
        System.out.println(TAG + "===toDeil===" + value);
        return value;
    }
}
