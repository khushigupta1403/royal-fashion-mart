package com.royalfashionmart.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
@CrossOrigin("*")
public class UploadController {

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        String path = "uploads/" + file.getOriginalFilename();
        file.transferTo(new File(path));

        return "Uploaded: " + file.getOriginalFilename();
    }
}