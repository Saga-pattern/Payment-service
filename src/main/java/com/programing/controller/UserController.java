package com.programing.controller;

import com.programing.entity.User;
import com.programing.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final PaymentService paymentService;

    @GetMapping
    public List<User> getAll() {return paymentService.getAll();}
}
