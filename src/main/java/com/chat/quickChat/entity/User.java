package com.chat.quickChat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String phoneNumber;
    private String emailId;
    private String password;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_room",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="room_id"))
    private List<Room> roomList;

}
