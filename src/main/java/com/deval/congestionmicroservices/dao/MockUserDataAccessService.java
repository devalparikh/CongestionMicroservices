package com.deval.congestionmicroservices.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.deval.congestionmicroservices.model.User;
import org.springframework.stereotype.Repository;

@Repository("mockDao")
public class MockUserDataAccessService implements UserDao {

    private static List<User> DB = new ArrayList<>();

    @Override
    public int insertUser(UUID id, User user) {
        DB.add(new User(id, user.getName()));
        return 1;
    }

    @Override
    public List<User> selectAllUsers() {
        return DB;
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return DB.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteUserById(UUID id) {
        Optional<User> userMaybe = selectUserById(id);
        if(userMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(userMaybe.get());
        return 1;

    }

    @Override
    public int updateUserById(UUID id, User updatedUser) {
        return selectUserById(id)
                .map(user -> {
                    int indexOfUserToUpdate = DB.indexOf(user);
                    if(indexOfUserToUpdate >= 0) {
                        DB.set(indexOfUserToUpdate, new User(id, updatedUser.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
