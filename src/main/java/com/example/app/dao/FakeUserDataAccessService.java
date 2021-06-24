package com.example.app.dao;

import com.example.app.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeUserDataAccessService implements UserDao {

    private static List<User> DB = new ArrayList<>();


    @Override
    public Optional<User> insertUser(UUID id, User user) {
        DB.add(new User(id, user.getFullName(), user.getEmail(), user.getDob()));
        return DB.stream().reduce((first, second) -> {
            return second;
        });
    }

    @Override
    public List<User> selectAllUsers() {
        return DB;
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteUserById(UUID id) {
        Optional<User> personMaybe = selectUserById(id);
        if (personMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public User updateUserById(UUID id, User update) {
        return selectUserById(id)
                .map(user -> {
                    int indexOfUserToUpdate = DB.indexOf(user);
                    if (indexOfUserToUpdate >= 0) {
                        DB.set(indexOfUserToUpdate, new User(id, update.getFullName(), update.getEmail(), update.getDob()));
                    }
                    return DB.get(indexOfUserToUpdate);
                })
                .orElse(null);
    }
}
