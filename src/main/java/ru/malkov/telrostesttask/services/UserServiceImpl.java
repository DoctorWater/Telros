package ru.malkov.telrostesttask.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.malkov.telrostesttask.entities.User;
import ru.malkov.telrostesttask.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link UserService} interface
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(@Autowired UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Get user with given id from the database
     *
     * @param id -- id of the required user
     * @return {@link User} entity found in the database
     * @throws EntityNotFoundException -- if an entity with the given id doesn't exist
     */
    @Override
    public User getById(Long id) throws EntityNotFoundException {
        return repository.getReferenceById(id);
    }

    /**
     * Get users with given ids from the database
     *
     * @param ids -- list of ids of the required users
     * @return {@link User} entities found in the database
     * @throws EntityNotFoundException -- if one of the given ids is not registered in the database
     */
    @Override
    public List<User> getById(List<Long> ids) throws EntityNotFoundException {
        List<User> result = new ArrayList<>();
        for (Long id : ids) {
            result.add(repository.getReferenceById(id));
        }
        return result;
    }

    /**
     * Uploads {@link User} entity in the database
     *
     * @param user -- the uploading entity
     */
    @Override
    public boolean save(User user) {
        try {
            if (user == null) {
                throw new IllegalArgumentException("Given user is null");   //By some reason throwing of IllegalArgumentException in repository.save() doesn't work.
            }
            repository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Removes an entity with the given id from the database.
     *
     * @param id -- id of the required user
     */
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    /**
     * Removes entities with the given ids from the database.
     *
     * @param ids --      * @param ids -- list of ids of the required users
     */
    @Override
    public void delete(List<Long> ids) {
        for (Long id : ids) {
            repository.deleteById(id);
        }

    }
}
