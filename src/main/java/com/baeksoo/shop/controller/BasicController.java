package com.baeksoo.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {

    @GetMapping("/") // 페이지 경로
    String hello(){
        return "static/index.html";
    }
}
