package com.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/info")
public class PublicController {
    @GetMapping()
    public String getInfo() {
        return "Allowed METHOD: GET";
    }

    @PostMapping()
    public String postInfo() {
        return "Allowed METHOD: POST";
    }

    @PutMapping()
    public String putInfo() {
        return "Allowed METHOD: PUT";
    }

    @DeleteMapping()
    public String deleteInfo() {
        return "Allowed METHOD: DELETE";
    }
}
