package com.assessment.Assessment.service;

import com.assessment.Assessment.exception.SocialMediaAppException;
import com.assessment.Assessment.model.Follower;
import com.assessment.Assessment.model.User;
import com.assessment.Assessment.repositories.FollowerRepo;
import com.assessment.Assessment.repositories.UserRepository;
import com.assessment.Assessment.request.CreateUserDto;
import com.assessment.Assessment.request.UpdateUserDto;
import com.assessment.Assessment.response.ResponseDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private FollowerRepo followerRepo;
    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseDetails createUser(CreateUserDto dto) throws SocialMediaAppException {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new SocialMediaAppException("User with this email exists already");
        } else {
            User user = new User();
            user.setUsername(dto.getUsername());
            user.setEmail(dto.getEmail());
            user.setPassword(encryptPassword(dto.getPassword()));
            user.setProfilePicture(dto.getProfilePicture());
            user.setIsActive(true);
            userRepository.save(user);
        }
        return createUserResponse();
    }

    @Override
    public void updateUser(UpdateUserDto dto) throws SocialMediaAppException {
        List<Follower> followers = new ArrayList<>();
        Follower follower = new Follower();
        follower.setUsername(dto.getFollowerList().iterator().next().getUsername());
        follower.setProfilePicture(dto.getFollowerList().iterator().next().getProfilePicture());
        followers.add(follower);
        followerRepo.save(follower);

        User user = getUserById(dto.getUserId());
        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if (dto.getChangeProfilePicture() != null) {
            user.setProfilePicture(dto.getChangeProfilePicture());
        }
        user.setFollowers(followers);
        user.setModifiedDate(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) throws SocialMediaAppException {
        return userRepository.findById(userId).orElseThrow(() ->
                new SocialMediaAppException("Id doesn't match any user object in the database. "));
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long userId) throws SocialMediaAppException {
        User user = getUserById(userId);
        if (userRepository.existsById((userId))) {
            userRepository.deleteById(userId);
            user.setIsActive(false);
            user.setModifiedDate(LocalDateTime.now());
        } else {
            throw new SocialMediaAppException("User with this id" + userId + " doesn't exist");
        }

    }

    private ResponseDetails createUserResponse() throws SocialMediaAppException {
        ResponseDetails responseDetails = new ResponseDetails();
        responseDetails.setMessage("user created successfully");
        responseDetails.setStatus("success");
        responseDetails.setTimestamp(LocalDateTime.now());
        return responseDetails;
    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }


}
