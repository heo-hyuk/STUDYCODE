package 콘솔게시판.controller;

import 콘솔게시판.model.Post;
import 콘솔게시판.service.AuthService;
import 콘솔게시판.service.PostService;

import java.util.List;
import java.util.Scanner;

public class AppController {

    public Scanner scn = new Scanner(System.in);
    public PostService postService = new PostService();
    public AuthService authService = new AuthService();

    public void run() {

        // 한 명 미리 가입시켜둠
        authService.init();

        // 게시글 두 개 미리 등록함
        postService.init();

        while(true) {
            // 만약 로그인이 되어 있다면
            if (authService.isLoggedIn()) {
                loggedInMenu();
                int cmd = scn.nextInt();

                switch (cmd) {
                    case 1 -> {
                        if (postService.findAll().isEmpty()) {
                            System.out.println("\n게시글이 존재하지 않습니다.\n");
                            continue;
                        }
                        for (Post post : postService.findAll()) {
                            System.out.println("번호: " + post.getId() +
                                    "번 / 제목: " + post.getTitle() + " / 작성자: " +
                                    post.getAuthor());
                        }
                        System.out.println();
                    }
                    case 2 -> {
                        postService.findById();
                    }
                    case 3 -> {
                        System.out.println("\n게시글 작성 메뉴입니다\n");

                        postService.add(authService
                                .getLoginUser()
                                .getUsername());
                    }
                    case 4 -> {
                        postService.modify(authService.getLoginUser().getUsername());
                    }
                    case 5 -> {
                        postService.delete(authService.getLoginUser());
                    }
                    case 6 -> {
                        authService.logout();
                    }
                    case 0 -> {
                        System.out.println("프로그램 종료");
                        return;
                    }
                }
            }else {
                notLoggedInMenu();
                int cmd = scn.nextInt();

                switch (cmd) {
                    case 1 -> {
                        if (postService.findAll().isEmpty()) {
                            System.out.println("\n게시글이 존재하지 않습니다.\n");
                            continue;
                        }
                        for (Post post : postService.findAll()) {
                            System.out.println("번호: " + post.getId() +
                                    "번 / 제목: " + post.getTitle() + " / 작성자: " +
                                    post.getAuthor());
                        }
                        System.out.println();
                    }
                    case 2 -> {
                        postService.findById();
                    }
                    case 3 -> {
                        // 로그인 로직
                        authService.login();
                    }
                    case 4 -> {
                        System.out.println("\n회원가입 메뉴입니다\n");

                        boolean signupResult = authService.signup();

                        if (signupResult) {
                            System.out.println("\n회원 가입이 완료되었습니다.\n");
                        }else {
                            System.out.println("\n이미 존재하는 회원입니다.\n");
                        }
                    }
                    case 0 -> {
                        System.out.println("프로그램 종료");
                        return;
                    }
                }
            }
        }

    }

    // 로그인을 안 했을 때 띄워줄 명령어
    private static void notLoggedInMenu() {
        System.out.println("=== 명령어 목록 ===");
        System.out.println("1. 게시글 목록");
        System.out.println("2. 게시글 상세보기");
        System.out.println("3. 로그인");
        System.out.println("4. 회원가입");
        System.out.println("0. 프로그램 종료");
        System.out.print("\n선택:");
    }

    // 로그인을 안 했을 때 띄워줄 명령어
    private static void loggedInMenu() {
        System.out.println("=== 명령어 목록 ===");
        System.out.println("1. 게시글 목록");
        System.out.println("2. 게시글 상세보기");
        System.out.println("3. 게시글 작성");
        System.out.println("4. 게시글 수정");
        System.out.println("5. 게시글 삭제");
        System.out.println("6. 로그아웃");
        System.out.println("0. 프로그램 종료");
        System.out.print("\n선택:");    }
}

