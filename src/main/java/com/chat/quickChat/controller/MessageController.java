package com.chat.quickChat.controller;

import com.chat.quickChat.entity.Message;
import com.chat.quickChat.entity.Room;
import com.chat.quickChat.exception.QuickChatException;
import com.chat.quickChat.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping
public class MessageController {

    /**
    On "/SendMessage/{roomKey}" we have to send the messages and
     on endpoint "/topic/chat/{roomKey}" we will receive all the messages.
     **/
    @Autowired
    RoomService roomService;
    @MessageMapping("/SendMessage/{roomKey}")//at app/sendMessage/{roomKey} we needed to send the subscribe message.
    @SendTo("/topic/chat/{roomKey}")
    public Message getMessages(Message message) throws QuickChatException {
        Room room=roomService.findByRoomKey("room-1");
        roomService.updateMessage(room,message);
        return message;
    }

}
