package com.mycinemalist.service;

import com.mycinemalist.DTO.UserDTO;
import com.mycinemalist.entity.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface FriendService {
    //User addFriend(String userId, String friendId);

    Optional<UserDTO> addFriend(String userId, String friendId);

    //User removeFriend(String userId, String friendId);

    Boolean removeFriend(String userId, String friendId);

    //List<String> getFriends(String userId);

    List<UserDTO> getFriends(String userId);
}
