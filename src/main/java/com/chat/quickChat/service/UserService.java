package com.chat.quickChat.service;

import com.chat.quickChat.entity.User;

public interface UserService {
    public User getUserById(Long userId);
    public User getUserByEmail(String emailId);
    public Long addUser(User user);
    public String updateEmailId(Long userId,String newEmailId);
    public String updatePassword(Long userId,String password);
}
