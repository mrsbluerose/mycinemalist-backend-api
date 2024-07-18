package com.mycinemalist.service;

import com.mycinemalist.entity.User;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> findByUserId(String userId);
}
