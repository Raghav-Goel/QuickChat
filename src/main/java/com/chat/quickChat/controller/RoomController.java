package com.chat.quickChat.controller;

import com.chat.quickChat.entity.Message;
import com.chat.quickChat.entity.Room;
import com.chat.quickChat.exception.QuickChatException;
import com.chat.quickChat.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@CrossOrigin("*")
public class RoomController {
    @Autowired
    RoomService roomService;
    //todo: Needed to implement pagination functionality.
    @GetMapping("roomId/{roomId}")
    public ResponseEntity<Room> getRoomByRoomId(@PathVariable long roomId,
           @RequestParam(value="page",defaultValue = "0",required = false) int page,
           @RequestParam(value="page",defaultValue = "20",required = false) int size) throws QuickChatException {
        Room room=roomService.findByRoomId(roomId);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }
    //todo: Needed to implement pagination functionality.
    @GetMapping("roomkey/{roomKey}")
    public ResponseEntity<Room> getRoomByRoomKey(@PathVariable String roomKey,
           @RequestParam(value="page",defaultValue = "0",required = false) int page,
           @RequestParam(value="page",defaultValue = "20",required = false) int size) throws QuickChatException {
        Room room=roomService.findByRoomKey(roomKey);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }
    @GetMapping("/getPublicRoom")
    public ResponseEntity<List<Room>> getPublicRoom(){
        List<Room> roomList=roomService.getPublicRooms();
        System.out.println("roomList "+roomList);
        return new ResponseEntity<>(roomList,HttpStatus.OK);
    }
    //todo: Needed to implement pagination functionality.
    @GetMapping("roomkey/message/{roomKey}")
    public ResponseEntity<List<Message>> getMessagesByRoomKey(@PathVariable String roomKey,
           @RequestParam(value="page",defaultValue = "0",required = false) int page,
           @RequestParam(value="page",defaultValue = "20",required = false) int size) throws QuickChatException {
        List<Message> listOfMessages=roomService.getMessageForSpecificRoomFromRoomKey(roomKey);
        return new ResponseEntity<>(listOfMessages, HttpStatus.OK);
    }
    @PostMapping("/createRoom")
    public ResponseEntity<Long> createRoom(@RequestBody Room room) throws QuickChatException {
        long roomId=roomService.createRoom(room);
        return new ResponseEntity<>(roomId,HttpStatus.CREATED);
    }
}
