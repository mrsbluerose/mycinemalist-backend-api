package com.mycinemalist.service;

import com.mycinemalist.entity.User;

import java.util.List;

public interface FriendService {
    User addFriend(String userId, String friendId);
    User removeFriend(String userId, String friendId);
    List<String> getFriends(String userId);
}
