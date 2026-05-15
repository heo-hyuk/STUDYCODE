package com.example.demo.board;

import com.example.demo.common.AuthState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final AuthState authState;

    @GetMapping("/posts")
    public String posts(Model model,
                        @RequestParam(required = false) String msg) {

        model.addAttribute("loginName", authState.getLoginName());
        model.addAttribute("loggedIn", authState.isLoggedIn());
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("msg", msg);

        return "posts";
    }
    @PostMapping("/posts")
    public String add(@RequestParam String title) {
        if (!authState.isLoggedIn()) return "redirect:/login";

        if (title == null || title.isBlank()) {
            return "redirect:/posts?msg=제목을 입력해주세요";
        }

        postService.add(title.trim(), authState.getLoginName());
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        if (!authState.isLoggedIn()) return "redirect:/login";

        boolean ok = postService.delete(id, authState.getLoginName());
        if (!ok) return "redirect:/posts?msg=작성자만 삭제할 수 있어요";
        return "redirect:/posts";
    }
}
