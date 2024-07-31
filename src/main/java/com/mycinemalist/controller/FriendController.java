package com.mycinemalist.controller;

import com.mycinemalist.dto.UserDTO;
import com.mycinemalist.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @PostMapping("/{userId}/add/{friendId}")
    public UserDTO addFriend(@PathVariable String userId, @PathVariable String friendId) {
        Optional<UserDTO> opt = friendService.addFriend(userId, friendId);
        if(opt.isPresent()) {
            return opt.get();
        } else {
            return null;
        }
    }

    @DeleteMapping("/{userId}/remove/{friendId}")
    public Boolean removeFriend(@PathVariable String userId, @PathVariable String friendId) {
        return friendService.removeFriend(userId, friendId);
    }

    @GetMapping("/{userId}")
    public List<UserDTO> getFriends(@PathVariable String userId) {
        return friendService.getFriends(userId);
    }
}
