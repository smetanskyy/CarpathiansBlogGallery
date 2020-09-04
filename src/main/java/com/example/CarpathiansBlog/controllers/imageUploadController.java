package com.example.CarpathiansBlog.controllers;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class imageUploadController {
    @GetMapping(value = {"get-image/{image}", "post/get-image/{image}"})
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("image") String image) {
        if (!image.equals("")) {
            try {
                Path fileName = Paths.get("src/main/resources/static/images", image);
                byte[] buffer = Files.readAllBytes(fileName);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
