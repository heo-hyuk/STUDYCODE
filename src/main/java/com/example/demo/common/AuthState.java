package com.example.demo.common;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AuthState {
    private boolean loggedIn = false; // 로그인 상태
    private String loginName = null; // 로그인한 유저의 이름 혹은 아이디

    public void login(String name) {
        this.loggedIn = true;
        this.loginName = name;
    }

    public void logout() {
        this.loggedIn = false;
        this.loginName = null;
    }


}