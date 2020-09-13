package com.deval.congestionmicroservices.service;

import com.deval.congestionmicroservices.dao.UserDao;
import com.deval.congestionmicroservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired // Inject into UserDao interface
    public UserService(@Qualifier("mockDao") UserDao userDao) {
        this.userDao = userDao;
    }

//    @Autowired // Inject into UserDao interface
//    public UserService(@Qualifier("mongo") UserDao userDao) {
//        this.userDao = userDao;
//    }

//    @Autowired // Inject into UserDao interface
//    public UserService(@Qualifier("postgres") UserDao userDao) {
//        this.userDao = userDao;
//    }


    public int addUser(User user) {
        return userDao.insertUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
    }

    public Optional<User> getUserById(UUID id) {
        return userDao.selectUserById(id);
    }

    public int deleteUser(UUID id) {
        return userDao.deleteUserById(id);
    }

    public int updateUser(UUID id, User newUser) {
        return userDao.updateUserById(id, newUser);
    }
}
