package com.chat.quickChat.repository;

import com.chat.quickChat.entity.Room;
import com.chat.quickChat.entity.RoomType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends CrudRepository<Room,Long> {
    Optional<Room> findByRoomKey(String roomKey);

    @Query("select count(r)>0 from Room r where r.roomKey=:roomKey")
    boolean checkIfRoomKeyExist(@Param("roomKey") String roomKey);

    Optional<List<Room>> findByRoomTypeEquals(RoomType roomType);
}
