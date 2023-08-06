package com.assessment.Assessment.controller;

import com.assessment.Assessment.exception.SocialMediaAppException;
import com.assessment.Assessment.model.User;
import com.assessment.Assessment.request.CreateUserDto;
import com.assessment.Assessment.request.UpdateUserDto;
import com.assessment.Assessment.response.ResponseDetails;
import com.assessment.Assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto userDto) throws SocialMediaAppException {
        userService.createUser(userDto);
        ResponseDetails response = userService.createUser(userDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDto dto) throws SocialMediaAppException {
        userService.updateUser(dto);
        return ResponseEntity.ok("Updated successfully");
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() throws SocialMediaAppException {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);


    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findUserById(@PathVariable Long userId) throws SocialMediaAppException {
        if(userId == null){
            throw new SocialMediaAppException("Id is null");
        }
        userService.getUserById(userId);
        return ResponseEntity.ok("user retrieved");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?>  deleteUserById(@PathVariable Long userId) throws SocialMediaAppException{
        userService.deleteUserById(userId);
        return ResponseEntity.ok("user deleted successfully");

    }
}
