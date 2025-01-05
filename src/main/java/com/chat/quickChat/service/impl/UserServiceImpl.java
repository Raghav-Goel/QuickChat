package com.chat.quickChat.service.impl;

import com.chat.quickChat.entity.User;
import com.chat.quickChat.repository.UserRepo;
import com.chat.quickChat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Override
    public User getUserById(Long userId){
        Optional<User> optionalUser=userRepo.findById(userId);
        return optionalUser.orElseThrow(()->new RuntimeException("Get error"));
    }

    @Override
    public User getUserByEmail(String emailId){
        if(emailId==null){
            throw new RuntimeException("Getdsdsd error");
        }
        Optional<User> optionalUser=userRepo.findByEmailId(emailId);
        return optionalUser.orElseThrow(()->new RuntimeException("Get error"));
    }

    @Override
    public Long addUser(User newUser) {
        //In this first we have to check whether the provided emailId is already being used or not.
        if (userRepo.findByEmailId(newUser.getEmailId()).isPresent()) {
            throw new RuntimeException("Get error");
        }
        userRepo.save(newUser);
        return newUser.getUserId();
    }
    @Override
    public String updateEmailId(Long userId, String newEmailId){
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("Get error"));
        user.setEmailId(newEmailId);
        return "EmailId is updated successfully.";
    }

    @Override
    public String updatePassword(Long userId, String password) {
        return null;
    }
}
