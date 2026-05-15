package com.example.demo.console.service;

import com.example.demo.console.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthService {
    private Scanner scn = new Scanner(System.in);
    public void init(){
        users.add(new User("k","1111"));
    }

    private List<User> users = new ArrayList<>();
    private User loginUser = null;

    // 로그인을 하면 true
    public boolean isLoggedIn() {
        return loginUser != null;
    }

    public User getLoginUser() {
        return loginUser;
    }

    // 특정 회원을 이름으로 찾는 로직
    private User findByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }

    public boolean signup() {
        System.out.print("이름: ");
        String name = scn.nextLine();

        System.out.print("비밀번호: ");
        String password = scn.nextLine();
        if (findByUsername(name) != null) return false;
        users.add(new User(name, password));
        return true;
    }


    public boolean login(String username, String password) {
        User user = findByUsername(username);
        if (user == null) return false;
        if (!user.getPassword().equals(password)) return false;

        loginUser = user;
        return true;
    }

    public void logout() {
        loginUser = null;
        System.out.println("\n로그아웃 완료\n");
    }
    public void login(){
        System.out.print("이름:");
        String name =scn.nextLine();

        System.out.print("비밀번호:");
        String password =scn.nextLine();

        for(User u:users)
        {
            if(u.getUsername().equals(name) && u.getPassword().equals(password)){
                System.out.println("\n로그인이 완료 되었습니다.\n");
                loginUser = u;
                break;
            }else{
                System.out.println("\n아이디 혹은 비밀번호가 일치하지 않습니다.\n");
            }
        }
    }

}