package ru.malkov.telrostesttask.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import ru.malkov.telrostesttask.entities.User;
import ru.malkov.telrostesttask.repositories.UserRepository;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserServiceImpl service;
    private final User dummy1 = new User(1L, "John", "Doe", "Alister", LocalDate.of(2002, 1, 22), "nothing@gmail.com", "+79110000000");
    private final User dummy2 = new User(2L, "Andrey", "Ivanov", "Olegovich", LocalDate.of(1989, 4, 11), "nothing@gmail.com", "+79110000000");
    @MockBean
    private UserRepository repository;


    @Test
    void getById_successful() {
        Mockito.when(repository.getReferenceById(1L)).thenReturn(dummy1);
        User user;

        user = service.getById(1L);

        assertThat(user.getName(), containsString("John"));
    }

    @Test
    void getManyById_successful() {
        Mockito.when(repository.getReferenceById(1L)).thenReturn(dummy1);
        Mockito.when(repository.getReferenceById(2L)).thenReturn(dummy2);
        List<User> users;

        users = service.getById(new ArrayList<>(Arrays.asList(1L, 2L)));

        Assert.notEmpty(users, "The list of users is empty.");
    }

    @Test
    void save_successful() {
        boolean isCreated = service.save(dummy1);

        Assert.isTrue(isCreated, "User was not saved.");
    }

    @Test
    void save_failed(){
        boolean isCreated = service.save(null);

        Assert.isTrue(!isCreated, "Null user was saved.");
    }

    @Test
    void delete_one_successful() {
        Mockito.doNothing().when(repository).deleteById(any(Long.class));

        service.delete(123L);

        Mockito.verify(repository, Mockito.times(1)).deleteById(123L);
    }

    @Test
    void delete_many_successful() {
        Mockito.doNothing().when(repository).deleteById(any(Long.class));

        service.delete(new ArrayList<>(Arrays.asList(1L, 2L)));

        Mockito.verify(repository, Mockito.times(2)).deleteById(any(Long.class));

    }


}