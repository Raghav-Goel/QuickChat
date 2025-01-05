package com.chat.quickChat.repository;

import com.chat.quickChat.entity.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends CrudRepository<Message,Long> {
}
