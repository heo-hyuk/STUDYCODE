package com.example.demo.console.service;

import com.example.demo.console.model.Post;
import com.example.demo.console.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostService {

    private Scanner scn = new Scanner(System.in);
    private List<Post> posts = new ArrayList<>();
    private int seq = 1;

    public void init() {
        posts.add(new Post(seq++, "제목1", "내용1", "아구몬"));
        posts.add(new Post(seq++, "제목2", "내용2", "피요몬"));
    }

    public void add(String author) {
        System.out.print("제목:");
        String title = scn.nextLine();

        System.out.print("내용:");
        String content = scn.nextLine();

        posts.add(new Post(seq++, title, content, author));

        System.out.println("\n게시글 작성이 완료되었습니다.\n");
    }

    // 게시글 리스트를 반환
    public List<Post> findAll() {
        return posts;
    }

    // 아이디(게시글 번호)를 통해 글 상세 보기
    public void findById() {
        System.out.println("\n게시글 상세 보기입니다.\n");
        System.out.print("글 번호:");
        int num = scn.nextInt();

        for (Post post : posts) {
            if (post.getId() == num) {
                System.out.println("번호: " + post.getId()
                        + "번 / 제목: " + post.getTitle()
                        + " / 내용: " + post.getContent()
                        + " / 작성자: " + post.getAuthor());
                break;
            }
        }
        System.out.println();

    }

    // 게시글 수정
    public void modify(String username) {

        System.out.print("수정할 게시글 번호:");
        int modifyNum = scn.nextInt();
        scn.nextLine();

        // 게시글 정보를 받아와야 되는데 지금 어떤 게시글이 들어갈지도 모르고
        // 안 들어갈 수도 있기 때문에 null을 넣어둠
        Post temp = null;

        for (Post post : posts) {
            if (post.getId() == modifyNum) {
                temp = post;
                break;
            }
        }


        if(temp != null) {
            // 가져온 게시글의 작성자 이름과 로그인 유저의 이름이 같으면 수정에 들어감
            if(temp.getAuthor().equals(username)) {
                System.out.print("제목:");
                String title = scn.nextLine();

                System.out.print("내용:");
                String content = scn.nextLine();

                temp.setTitle(title);
                temp.setContent(content);

                System.out.println("\n게시글이 정상적으로 수정되었습니다.\n");
            }else {
                System.out.println("\n게시글 수정 권한이 없습니다.\n");
            }
        }else {
            System.out.println("\n게시글 존재하지 않습니다.\n");
        }
    }


    // 게시글 삭제
    public void delete(User loginUser) {

        // 현재 로그인한 유저의 이름
        String loginUserName = loginUser.getUsername();

        System.out.print("삭제할 게시글 번호:");
        int delNo = scn.nextInt();

        boolean postCheck = false;

        for (Post post : posts) {
            if(post.getId() == delNo) {
                if (post.getAuthor().equals(loginUserName)) {
                    posts.remove(post);
                    System.out.println("\n" + delNo + "번 게시글을 삭제하였습니다.\n");
                    postCheck = true;
                    break;
                }else {
                    System.out.println("\n게시글 삭제 권한이 없습니다.\n");
                    return;
                }
            }
        }

        if (!postCheck) {
            System.out.println("\n해당 게시글은 존재하지 않습니다.\n");
        }
    }
}