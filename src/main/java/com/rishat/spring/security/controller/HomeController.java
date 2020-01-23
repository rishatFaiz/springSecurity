package com.rishat.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

  @GetMapping
  public String home(Principal principal) {
    if (principal != null) {
      return  "Welcome " + principal.getName();
    }
    return "home";
  }
}
