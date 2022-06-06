package com.hs.checkhealth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckHealth {
    @PostMapping("check")
    public String check() {
        return "ok";
    }
}
