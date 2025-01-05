package com.chat.quickChat.service.impl;

import com.chat.quickChat.entity.Message;
import com.chat.quickChat.entity.Room;
import com.chat.quickChat.entity.RoomType;
import com.chat.quickChat.exception.QuickChatException;
import com.chat.quickChat.repository.RoomRepo;
import com.chat.quickChat.service.RoomService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepo roomRepo;
    @Autowired
    Environment environment;
    @Override
    public Room findByRoomId(Long roomId) throws QuickChatException {
        Optional<Room> optionalRoom=roomRepo.findById(roomId);
        return optionalRoom.orElseThrow(()->new QuickChatException(environment.getProperty("Service.ROOM_NOT_FOUND")));
    }

    @Override
    public Room findByRoomKey(String roomKey) throws QuickChatException {
        Optional<Room> optionalRoom=roomRepo.findByRoomKey(roomKey);
        return optionalRoom.orElseThrow(()->new QuickChatException(environment.getProperty("Service.ROOM_NOT_FOUND")));
    }

    @Override
    public Long createRoom(Room room) throws QuickChatException {
        if(checkIfRoomKeyExist(room.getRoomKey())){
            throw new QuickChatException(environment.getProperty("Service.ROOM_KEY_ALREADY_PRESENT"));
        }
        if(room.getCreatedAt()==null)room.setCreatedAt(LocalDateTime.now());
        room.setMessages(new ArrayList<>());
        roomRepo.save(room);
        return room.getRoomId();
    }
    @Override
    public boolean checkIfRoomKeyExist(String roomKey){
        return roomRepo.checkIfRoomKeyExist(roomKey);
    }
    @Override
    public List<Message> getMessageForSpecificRoom(Long roomId) throws QuickChatException {
        Room room=findByRoomId(roomId);
        return room.getMessages();
    }

    @Override
    public List<Message> getMessageForSpecificRoomFromRoomKey(String roomKey) throws QuickChatException {
        Room room=findByRoomKey(roomKey);
        return room.getMessages();
    }
    @Override
    @Transactional
    public void updateMessage(Room room,Message message){
        message.setLocalDateTime(LocalDateTime.now());
        room.getMessages().add(message);
        roomRepo.save(room);
    }

    @Override
    public String deleteRoomByRoomId(Long roomId) throws QuickChatException {
        Room room=findByRoomId(roomId);
        roomRepo.deleteById(roomId);
        return "Room got deleted successfully";
    }

    @Override
    public String deleteRoomByRoomKey(String roomKey) throws QuickChatException {
        Room room=findByRoomKey(roomKey);
        roomRepo.delete(room);
        return "Room got deleted successfully";
    }

    @Override
    public List<Room> getPublicRooms() {
        Optional<List<Room>> room=roomRepo.findByRoomTypeEquals(RoomType.Public);
        return room.orElseThrow(()->new RuntimeException("Got error which fetching public rooms"));
    }
}
