package com.mycinemalist.service;

import com.mycinemalist.entity.User;

public interface UserService {
    User createUser(User user);
    User findByUsername(String username);
}
