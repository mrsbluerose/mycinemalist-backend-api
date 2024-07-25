package com.mycinemalist.service;

import com.mycinemalist.dto.UserDTO;
import com.mycinemalist.entity.User;
import com.mycinemalist.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserDTO> addFriend(String userId, String friendId) {
        ObjectId userObjectId = new ObjectId(userId);
        ObjectId friendObjectId = new ObjectId(friendId);

        Optional<User> optUser = userRepository.findById(userObjectId);

        if (optUser.isPresent() && !optUser.get().getFriends().contains(friendObjectId)) {
            User user = optUser.get();
            List<ObjectId> userFriendList = user.getFriends();
            userFriendList.add(friendObjectId);
            user.setFriends(userFriendList);
            userRepository.save(user);

            Optional<User> friend = userRepository.findById(friendObjectId);
            if (friend.isPresent()) {
                UserDTO friendDTO = UserDTO.builder()
                        .id(friend.get().getId().toString())
                        .email(friend.get().getEmail())
                        .displayName(friend.get().getDisplayName())
                        .build();
                return Optional.of(friendDTO);
            }
        }

        return Optional.empty();
    }

    @Override
    public Boolean removeFriend(String userId, String friendId) {
        ObjectId userObjectId = new ObjectId(userId);
        ObjectId friendObjectId = new ObjectId(friendId);

        Optional<User> optUser = userRepository.findById(userObjectId);
        if (optUser.isPresent() && optUser.get().getFriends().contains(friendObjectId)) {
            User user = optUser.get();
            List<ObjectId> userFriendList = user.getFriends();
            userFriendList.remove(friendObjectId);
            user.setFriends(userFriendList);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<UserDTO> getFriends(String userId) {
        List<UserDTO> rtn = new ArrayList<>();

        ObjectId userObjectId = new ObjectId(userId);

        Optional<User> user = userRepository.findById(userObjectId);
        if(user.isPresent()){

            for(ObjectId friendObjectId : user.get().getFriends()) {
                Optional<User> friend = userRepository.findById(friendObjectId);
                if(friend.isPresent()) {
                    UserDTO userDTO = UserDTO.builder()
                            .id(friendObjectId.toString())
                            .email(friend.get().getEmail())
                            .displayName(friend.get().getDisplayName())
                            .build();
                    rtn.add(userDTO);
                }
            }

        }
        return rtn;
    }
}
