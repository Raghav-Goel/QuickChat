package com.chat.quickChat.repository;

import com.chat.quickChat.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    public Optional<User> findByEmailId(String emailId);
}
