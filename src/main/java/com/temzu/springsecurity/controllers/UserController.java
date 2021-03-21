package com.temzu.springsecurity.controllers;

import com.temzu.springsecurity.model.entity.User;
import com.temzu.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/score/inc")
    public User incUserScore(Principal principal, @RequestParam(defaultValue = "1", required = false) Integer inc) {
        User user = getUserByPrincipal(principal);
        user.setScore(user.getScore() + Math.abs(inc));
        return userService.saveOrUpdate(user);
    }

    @GetMapping("/score/dec")
    public User decUserScore(Principal principal, @RequestParam(defaultValue = "1", required = false) Integer dec) {
        User user = getUserByPrincipal(principal);
        user.setScore(user.getScore() - Math.abs(dec));
        return userService.saveOrUpdate(user);
    }

    @GetMapping("/score/get/current")
    public ResponseEntity<Integer> getCurrentScore(Principal principal) {
        User user = getUserByPrincipal(principal);
        return new ResponseEntity<>(user.getScore(), HttpStatus.OK);
    }

    @GetMapping("/score/get/{id}")
    public ResponseEntity<Integer> getScoreByUserId(@PathVariable Long id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with id: \"'%s'\" not found", id)));
        return new ResponseEntity<>(user.getScore(), HttpStatus.OK);
    }

    private User getUserByPrincipal(Principal principal) {
        return userService.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("unable to find user by username: " + principal.getName()));
    }
}
