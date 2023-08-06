package com.assessment.Assessment.request;

import com.assessment.Assessment.model.Follower;
import lombok.Data;

import java.util.List;


@Data
public class UpdateUserDto {
    private String changeProfilePicture;
    private String username;
    private Long userId;

    private List<Follower> followerList;
}
