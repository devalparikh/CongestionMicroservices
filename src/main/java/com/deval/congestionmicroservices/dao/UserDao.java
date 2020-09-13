package com.deval.congestionmicroservices.dao;

// Define contract of User interface
// Used for dependency injection

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.deval.congestionmicroservices.model.User;

public interface UserDao {

    // Insert new user with given ID
    int insertUser(UUID id, User user);

    // Insert new user with no given id (generate an id)
    default int insertUser(User user) {
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }

    List<User> selectAllUsers();

    Optional<User> selectUserById(UUID id);

    int deleteUserById(UUID id);

    int updateUserById(UUID id, User user);



}
