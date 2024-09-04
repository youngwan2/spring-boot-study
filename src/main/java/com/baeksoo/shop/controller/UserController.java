package com.baeksoo.shop.controller;

import com.baeksoo.shop.custom.CustomUser;
import com.baeksoo.shop.dto.UserDto;
import com.baeksoo.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //   로그인 화면
    @GetMapping("/login")
    String login() {
        return "login.html";
    }

    //    회원가입 화면
    @GetMapping("/signup")
    String signup(Model model) {
        return "signup.html";
    }

    // 유저 세부 페이지
    @GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable Long id) {
        UserDto user = userService.getUser(id);

        return user;
    }


    // 마이페이지 화면
    @GetMapping("/my-page")
    String myPage(Authentication authentication) {
//        System.out.println(authentication);
//        System.out.println(authentication.getName()); // 아이디 출력
//        System.out.println(authentication.isAuthenticated()); // 로그인 유무 검사
//        System.out.println(authentication.getAuthorities().contains(new SimpleGrantedAuthority("User"))); // 권한이 일반 유저인지
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        System.out.println(customUser.displayName);
        return "mypage.html";
    }

    //    유저 등록
    @PostMapping("/register")
    String register(@RequestParam String email, @RequestParam String password, Model model) {

        // null, undefined 확인
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return "signup:/signup";
        }

        try {
            userService.addUser(email, password); // 성공 시
            return "redirect:/login";

        } catch (Exception ex) { // 실패 시
            System.out.println(ex.getMessage());
            return "redirect:/signup";
        }
    }
}
