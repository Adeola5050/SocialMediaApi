package com.assessment.Assessment.service;

import com.assessment.Assessment.model.Post;
import com.assessment.Assessment.model.User;
import org.springframework.stereotype.Service;
@Service
public interface LikeService {

    public void likePost(User user, Post post);
}