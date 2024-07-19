package dev.muhammad.learnsendingemails.service;

import dev.muhammad.learnsendingemails.domain.User;

public interface UserService {
    User saveUser(User user);
    Boolean verifyToken(String token);
}
