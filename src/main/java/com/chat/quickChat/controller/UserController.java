package com.chat.quickChat.controller;

import com.chat.quickChat.entity.User;
import com.chat.quickChat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/getUser/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id){
        User user=userService.getUserById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "User found", user));
    }
    @GetMapping("/emailId/{emailId}")
    public ResponseEntity<ApiResponse<User>> getUserByEmailId(@PathVariable String emailId){
        User user=userService.getUserByEmail(emailId);
        return ResponseEntity.ok(new ApiResponse<>(true, "User found", user));
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> login(@RequestBody User loginUser) {
        User user = userService.getUserByEmail(loginUser.getEmailId());
        System.out.println(user);
        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Login successful", user));
        } else {
            return ResponseEntity.status(401).body(new ApiResponse<>(false, "Invalid email or password", null));
        }
    }
    @PostMapping("/addUser")
    public ResponseEntity<ApiResponse<Long>> addUser(@RequestBody User user){
        Long userId= userService.addUser(user);
        return ResponseEntity.ok(new ApiResponse<>(true, "User added", userId));
    }
}
