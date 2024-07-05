package com.mycinemalist.controller;

import com.mycinemalist.entity.User;
import com.mycinemalist.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @PostMapping("/{userId}/add/{friendId}")
    public User addFriend(@PathVariable String userId, @PathVariable String friendId) {
        return friendService.addFriend(userId, friendId);
    }

    @DeleteMapping("/{userId}/remove/{friendId}")
    public User removeFriend(@PathVariable String userId, @PathVariable String friendId) {
        return friendService.removeFriend(userId, friendId);
    }

    @GetMapping("/{userId}")
    public List<String> getFriends(@PathVariable String userId) {
        return friendService.getFriends(userId);
    }
}
