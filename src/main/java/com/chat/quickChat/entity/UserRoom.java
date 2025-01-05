package com.chat.quickChat.entity;

import jakarta.persistence.ManyToMany;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserRoom {
    Long userRoomId;

    Long userId;
    Long roomId;
    LocalDateTime dateOfJoin;
    List<Message> messageList=new ArrayList<>();;
}
