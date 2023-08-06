package com.assessment.Assessment;

import com.assessment.Assessment.model.Follower;
import com.assessment.Assessment.model.User;
import com.assessment.Assessment.repositories.UserRepository;
import com.assessment.Assessment.response.ResponseDetails;
import com.assessment.Assessment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
public class UserTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    public UserTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    void testThatCreateUser() {
        User createdUser = new User();
        String username = "esther#090";
        String email = "EstherGirl";
        String password = "password123";
        String profilePicture = "image009";

        createdUser.setUsername(username);
        createdUser.setEmail(email);
        createdUser.setPassword(password);
        createdUser.setProfilePicture(profilePicture);
        createdUser.setIsDeleted(false);
        createdUser.setIsActive(true);
        createdUser.setCreatedDate(LocalDateTime.now());
        assertEquals(username, createdUser.getUsername());
        assertEquals(email, createdUser.getEmail());
        log.info("{}-->", createdUser);


        ResponseDetails response = new ResponseDetails();
        response.setStatus("success");
        response.setMessage("User created successfully");
        response.setTimestamp(LocalDateTime.now());
        assertThat(response.getMessage()).isEqualTo("User created successfully");
        assertThat(response).isNotNull();
        System.out.println(response);

    }

    @Test
    void testThatUpdateUser() {
        User updatedUser = new User();
        List<Follower> followerList = new ArrayList<>();
        Follower follower = new Follower();
        follower.setUsername("geogia");
        follower.setProfilePicture("geogeiaImg1009");
        followerList.add(follower);

        Follower following = new Follower();
        following.setUsername("tee");
        following.setProfilePicture("teagirlImg1010");
        followerList.add(following);


        updatedUser.setEmail("estheradeola009@gmail.com");
        updatedUser.setUsername("Ade5090");
        updatedUser.setIsDeleted(false);
        updatedUser.setFollowers(followerList);
        updatedUser.setModifiedDate(LocalDateTime.now());
        assertThat(updatedUser.getUsername()).isNotNull();
        assertEquals(followerList, updatedUser.getFollowers());

        System.out.println(updatedUser);


    }

    @Test
    void testThatFindAllUser() {
        List<User> users = (List<User>) userRepository.findAll();
        assertThat(users.size()).isNotNull();
        System.out.println(users);

    }

    @Test
    void testThatDeleteUser() {
//        User


    }

    @Test
    void testThatGetUserById(){
        Long userId= 1L;
        Optional<User> user= userRepository.findById(userId);
        User expectedUser= new User();
        expectedUser.setId(userId);
        expectedUser.setUsername("Ben");

        assertThat(user).isNotNull();


    }

}
