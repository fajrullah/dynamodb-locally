package com.example.controller;

import com.example.domain.Account;
import com.example.entity.Student;
import com.example.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class PublicController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String getInfo(){
        return "this /public API";
    }
}