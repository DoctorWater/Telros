package ru.malkov.telrostesttask.services;

import ru.malkov.telrostesttask.entities.User;

import java.util.List;

/**
 * Defines interface for user service working with
 * {@link ru.malkov.telrostesttask.repositories.UserRepository}
 */

public interface UserService {

    User getById(Long id);

    List<User> getById(List<Long> ids);

    boolean save(User user);

    boolean delete(Long id);

    boolean delete(List<Long> ids);
}
