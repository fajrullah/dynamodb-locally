//package com.example.controller;
//
//import com.example.domain.Account;
//import com.example.service.AccountService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api")
//public class AccountController {
//
//    @Autowired
//    private AccountService accountService;
//
//    @GetMapping("/account/{accountId}")
//    public Mono<Account> getAccount(@PathVariable("accountId") String accountId) {
//        return accountService.findByAccountId(accountId);
//    }
//}