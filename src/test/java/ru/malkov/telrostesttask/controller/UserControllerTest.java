package ru.malkov.telrostesttask.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.malkov.telrostesttask.dto.UserContactInfoDto;
import ru.malkov.telrostesttask.dto.UserDto;
import ru.malkov.telrostesttask.entities.User;
import ru.malkov.telrostesttask.mappers.UserInfoMapper;
import ru.malkov.telrostesttask.mappers.UserMapper;
import ru.malkov.telrostesttask.services.UserServiceImpl;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserControllerTest {
    @MockBean
    UserServiceImpl userService;
    @Autowired
    UserInfoMapper infoMapper;
    @Autowired
    UserMapper userMapper;
    UserController controller;
    private User dummy1;
    private User dummy2;

    @BeforeEach
    void preparation(){
        //Some methods change our test objects, so we should reassign them each time
        dummy1 = new User(1L, "John", "Doe", "Alister", LocalDate.of(2002, 1, 22), "nothing1@gmail.com", "+79110000000");
        dummy2 = new User(2L, "Andrey", "Ivanov", "Olegovich", LocalDate.of(1989, 4, 11), "nothing2@gmail.com", "+79110000000");
        Mockito.when(userService.getById(1L)).thenReturn(dummy1);
        Mockito.when(userService.getById(2L)).thenReturn(dummy2);
        controller = new UserController(userService, infoMapper, userMapper);
    }

    @Test
    void getContactInfo_successful() {
        UserContactInfoDto dto = controller.getContactInfo(1L);

        assertThat(dto.email(), containsString("nothing1@gmail.com"));
    }

    @Test
    void updateByDto_successful() {
        UserContactInfoDto dto = new UserContactInfoDto("+79119102222", "something@gmail.com");

        controller.updateByDto(dto, 1L);

        assertThat(userService.getById(1L).getPhoneNumber(), containsString("+79119102222"));
    }

    @Test
    void deleteContactInfo_successful() {
        controller.deleteContactInfo(1L);

        assertThat(userService.getById(1L).getPhoneNumber(), nullValue());
    }

    @Test
    void get_successful() {
        User user = controller.get(1L);

        assertThat(user.getName(), containsString("John"));
    }

    @Test
    void get_many_successful() {
        Mockito.when(userService.getById(new ArrayList<>(Arrays.asList(1L, 2L)))).thenReturn(new ArrayList<>(Arrays.asList(dummy1, dummy2)));
        List<User> users = controller.get(new ArrayList<>(Arrays.asList(1L, 2L)));

        assertThat(users.size(), equalTo(2));
    }

    @Test
    void save() {
        Mockito.when(userService.save(any(User.class))).thenReturn(true);

        String message = controller.save(new UserDto("John", "Doe", "Alister", LocalDate.of(2002, 1, 22), "nothing1@gmail.com", "+79110000000"));

        assertThat(message, containsString("Saved successfully"));
    }

    @Test
    void delete_one_successful() {
        Mockito.doNothing().when(userService).delete(any(Long.class));

        controller.delete(123L);

        Mockito.verify(userService, Mockito.times(1)).delete(123L);
    }

    @Test
    void delete_many_successful() {
        Mockito.doNothing().when(userService).delete(Mockito.<ArrayList<Long>>any());

        controller.delete(new ArrayList<>(Arrays.asList(1L, 2L)));

        Mockito.verify(userService, Mockito.times(1)).delete(Arrays.asList(1L, 2L));
    }
}