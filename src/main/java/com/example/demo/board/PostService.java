package com.example.demo.board;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final List<Post> posts = new ArrayList<>();
    private long seq = 0;

    public List<Post> findAll() {
        return posts;
    }
    public void add(String title, String author) {
        seq++;
        posts.add(new Post(seq, title, author));
    }

    public boolean delete(long id, String loginName) {
        // 작성자만 삭제 가능(간단 권한)
        for (int i = 0; i < posts.size(); i++) {
            Post p = posts.get(i);
            if (p.getId() == id) {
                if (!p.getAuthor().equals(loginName)) return false;
                posts.remove(i);
                return true;
            }
        }
        return false;
    }

}
