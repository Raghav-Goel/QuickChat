package com.chat.quickChat.service;

import com.chat.quickChat.entity.Message;
import com.chat.quickChat.entity.Room;
import com.chat.quickChat.exception.QuickChatException;

import java.util.List;

public interface RoomService {
    Room findByRoomId(Long roomId) throws QuickChatException;
    Room findByRoomKey(String roomKey) throws QuickChatException;
    Long createRoom(Room room) throws QuickChatException;
    public boolean checkIfRoomKeyExist(String roomKey);
    List<Message> getMessageForSpecificRoom(Long roomId) throws QuickChatException;
    public List<Message> getMessageForSpecificRoomFromRoomKey(String roomKey) throws QuickChatException;
    public void updateMessage(Room room,Message message);
    String deleteRoomByRoomId(Long roomId) throws QuickChatException;
    String deleteRoomByRoomKey(String roomKey) throws QuickChatException;

    List<Room> getPublicRooms();
}
