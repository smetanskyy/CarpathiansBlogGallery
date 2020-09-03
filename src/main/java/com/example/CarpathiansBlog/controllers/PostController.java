package com.example.CarpathiansBlog.controllers;

import com.example.CarpathiansBlog.models.Post;
import com.example.CarpathiansBlog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/add-post")
    public String addPost(Model model) {
        //Iterable<Post> posts = postRepository.findAll();
        //model.addAttribute("posts", posts);
        model.addAttribute("pageTitle", "Posts page");
        return "add-post";
    }
}
