package com.assessment.Assessment.service;

import com.assessment.Assessment.exception.SocialMediaAppException;
import com.assessment.Assessment.model.User;
import com.assessment.Assessment.request.CreateUserDto;
import com.assessment.Assessment.request.UpdateUserDto;
import com.assessment.Assessment.response.ResponseDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    ResponseDetails createUser(CreateUserDto dto) throws SocialMediaAppException;

    void updateUser(UpdateUserDto dto) throws SocialMediaAppException;

    User getUserById(Long userId) throws SocialMediaAppException;

    List<User> findAll() throws SocialMediaAppException;

    void deleteUserById(Long userId) throws SocialMediaAppException;


}
