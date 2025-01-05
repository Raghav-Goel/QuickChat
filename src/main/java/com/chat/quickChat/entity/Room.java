package com.chat.quickChat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long roomId;
    String roomKey;
    String name;
    LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    RoomType roomType;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="room_id")
    List<Message> messages=new ArrayList<>();
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_room",joinColumns = @JoinColumn(name="room_id"),inverseJoinColumns = @JoinColumn(name="user_id"))
    private List<User> userList;
}
