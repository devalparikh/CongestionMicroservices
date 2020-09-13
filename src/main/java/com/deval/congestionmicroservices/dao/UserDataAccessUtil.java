package com.deval.congestionmicroservices.dao;

import com.deval.congestionmicroservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class UserDataAccessUtil implements UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final String personTableName;

    @Autowired
    public UserDataAccessUtil(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.personTableName = "person";
    }

    @Override
    public int insertUser(UUID id, User user) {
//        final String insertUserQuery = String.format("INSERT INTO %s (id, name) VALUES (?, ?);",
//                personTableName);
//        jdbcTemplate.query(insertUserQuery, new Object[]{id, user.getName()}, ((resultSet, i) -> {
//            return 1;
//        }));
        final String insertUserQuery = String.format("INSERT INTO %s (id, name) VALUES (?, ?);",
                personTableName);
        jdbcTemplate.update(insertUserQuery, new Object[]{id, user.getName()});
        return 1;
    }

    @Override
    public List<User> selectAllUsers() {
        final String allUsersQuery = String.format("SELECT id, name FROM %s", personTableName);
        List<User> users = jdbcTemplate.query(allUsersQuery, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new User(id,name);
        });
        return users;
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        final String useByIDQuery = String.format("SELECT id, name FROM %s WHERE id = ?");
        User curUser = jdbcTemplate.queryForObject(useByIDQuery, new Object[]{id}, ((resultSet, i) -> {
            UUID curUserId = UUID.fromString(resultSet.getString("id"));
            String curUserNaem = resultSet.getString("name");
            return new User(curUserId, curUserNaem);
        }));
        return Optional.ofNullable(curUser);
    }

    @Override
    public int deleteUserById(UUID id) {
        return 0;
    }

    @Override
    public int updateUserById(UUID id, User user) {
        return 0;
    }
}
