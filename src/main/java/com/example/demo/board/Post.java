package com.example.demo.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Post {
    private long id;
    private String title;
    private String author;
}
