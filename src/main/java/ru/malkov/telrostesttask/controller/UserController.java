package ru.malkov.telrostesttask.controller;

import org.springframework.web.bind.annotation.*;
import ru.malkov.telrostesttask.dto.UserContactInfoDto;
import ru.malkov.telrostesttask.dto.UserDto;
import ru.malkov.telrostesttask.entities.User;
import ru.malkov.telrostesttask.mappers.UserInfoMapper;
import ru.malkov.telrostesttask.services.UserService;

import java.util.List;

/**
 * REST-controller; provides interactions with HTTP-requests, sent to "/user/..." endpoints. You can
 * define which endpoints will be available for which role. Endpoints "/user/info/..." provide
 * information only for users' contact data.
 */

@RestController
@RequestMapping(value = "user")
public class UserController {

    private final UserService userService;
    private final UserInfoMapper infoMapper;

    public UserController(UserService userService, UserInfoMapper infoMapper) {
        this.userService = userService;
        this.infoMapper = infoMapper;
    }

    
    @GetMapping(value = "/info/get")
    public UserContactInfoDto getContactInfo(@RequestParam Long id) {
        User user = userService.getById(id);
        return new UserContactInfoDto(user.getPhoneNumber(), user.getEmail());
    }

    
    @PatchMapping(value = "/info/update")
    public String updateByDto(@RequestBody UserContactInfoDto dto, @RequestParam Long id) {
        User user = userService.getById(id);
        infoMapper.updateUserFromInfoDto(dto, user);
        userService.save(user);
        return "Updated successfully";
    }

    
    @PatchMapping(value = "/info/delete")
    public String deleteContactInfo(@RequestParam Long id) {
        UserContactInfoDto dto = new UserContactInfoDto(null, null);
        User user = userService.getById(id);
        infoMapper.updateUserFromInfoDto(dto, user);
        userService.save(user);
        return "Updated successfully";
    }

    
    @GetMapping(value = "/get/one")
    public User get(@RequestParam Long id) {
        return userService.getById(id);
    }

    
    @GetMapping(value = "/get/list")
    public List<User> get(@RequestParam List<Long> ids) {
        return userService.getById(ids);
    }

    
    @PostMapping(value = "/save")
    public String save(@RequestBody User user) {
        if (userService.save(user)) {
            return "Saved successfully";
        }
        return "Something went wrong during saving the user";
    }

    
    @DeleteMapping(value = "/delete/one")
    public String delete(@RequestParam Long id) {
        if(userService.delete(id)){
            return "Deleted successfully";
        }
        return "Something went wrong during deleting the user";
    }

    
    @DeleteMapping(value = "/delete/list")
    public String delete(@RequestParam List<Long> ids) {
        if(userService.delete(ids)) {
            return "Deleted successfully";
        }
        return "Something went wrong during deleting the user";
    }
}
