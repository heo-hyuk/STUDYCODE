package com.example.demo.auth;

import com.example.demo.common.AuthState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthState authState;

    @GetMapping("/")
    public String home() {
        return "home";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    // 로그인: 이름만 저장하는 초간단 버전
    @PostMapping("/login")
    public String login(@RequestParam String name) {
        if (name == null || name.isBlank()) {
            return "redirect:/login";
        }
        authState.login(name.trim());
        return "redirect:/posts";
    }

    @PostMapping("/logout")
    public String logout() {
        authState.logout();
        return "redirect:/";
    }
}
