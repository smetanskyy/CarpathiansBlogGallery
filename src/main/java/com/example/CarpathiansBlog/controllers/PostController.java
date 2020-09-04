package com.example.CarpathiansBlog.controllers;

import com.example.CarpathiansBlog.models.Post;
import com.example.CarpathiansBlog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
public class PostController {
    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/post/add-post")
    public String addPostTest(Model model) {
        model.addAttribute("pageTitle", "Posts page");
        return "add-post";
    }

    @PostMapping("/add-post")
    public String loadFile(@RequestParam("file") MultipartFile file,
                           @RequestParam String title,
                           @RequestParam String anons,
                           @RequestParam String fullText,
                           Model model
    ) {
        Post post = new Post();
        post.setAnons(anons);
        post.setFullText(fullText);
        post.setTitle(title);
        post.setViews(0);
        if (file != null) {
            Path path = Paths.get("src/main/resources/static/images");
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + file.getOriginalFilename();
            try {
                InputStream inputStream = file.getInputStream();
                Files.copy(inputStream, path.resolve(resultFileName), StandardCopyOption.REPLACE_EXISTING);
                post.setImage(resultFileName);
                postRepository.save(post);

            } catch (Exception e) {
                return "redirect:/";
            }
        }
        model.addAttribute("pageTitle", "Posts page");
        return "redirect:/";
    }
}
