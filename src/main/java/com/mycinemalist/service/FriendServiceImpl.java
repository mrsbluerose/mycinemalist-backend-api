package com.mycinemalist.service;

import com.mycinemalist.entity.User;
import com.mycinemalist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addFriend(String userId, String friendId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null && !user.getFriends().contains(friendId)) {
            user.getFriends().add(friendId);
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public User removeFriend(String userId, String friendId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null && user.getFriends().contains(friendId)) {
            user.getFriends().remove(friendId);
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public List<String> getFriends(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user != null ? user.getFriends() : null;
    }
}
