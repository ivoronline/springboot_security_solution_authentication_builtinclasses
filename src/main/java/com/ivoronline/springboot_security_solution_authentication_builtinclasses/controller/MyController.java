package com.ivoronline.springboot_security_solution_authentication_builtinclasses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

  //PROPERTIES
  @Autowired AuthenticationManager authenticationManager;

  //========================================================================
  // AUTHENTICATE
  //========================================================================
  @RequestMapping("Authenticate")
  String authenticate(@RequestParam String username, @RequestParam String password) {

    //CREATE AUTHENTICATION OBJECT (with Entered Username & Password)
    Authentication inputAuthentication = new UsernamePasswordAuthenticationToken(username, password);

    //GET    AUTHENTICATION OBJECT (with added Authorities)
    Authentication outputAuthentication = authenticationManager.authenticate(inputAuthentication); //Exception

    //STORE  AUTHENTICATION OBJECT (into Context)
    SecurityContextHolder.getContext().setAuthentication(outputAuthentication);

    //RETURN STATUS
    return "User Authenticated";

  }

  //========================================================================
  // HELLO
  //========================================================================
  @Secured("ROLE_USER")
  @RequestMapping("Hello")
  String hello() {
    return "Hello from Restricted Resource";
  }

}

