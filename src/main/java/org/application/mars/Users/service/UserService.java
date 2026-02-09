package org.application.mars.Users.service;

import org.application.mars.Users.models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(UUID id);
    User getUserByEmail(String email);
    User createUser(User user);
    void delete(UUID id); // match implementation
}
