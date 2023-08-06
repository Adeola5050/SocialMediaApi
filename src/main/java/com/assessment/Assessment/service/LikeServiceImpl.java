package com.assessment.Assessment.service;

import com.assessment.Assessment.model.Like;
import com.assessment.Assessment.model.Post;
import com.assessment.Assessment.model.User;
import com.assessment.Assessment.repositories.LikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LikeServiceImpl implements LikeService{
    @Autowired
    private LikeRepository likeRepository;
    @Override
    public void likePost(User user, Post post) {
        Like like = new Like();
        int likesCount= 0;
        like.setUser(user);
        like.setPost(post);
        like.setLikesCount(likesCount + 1);
        likeRepository.save(like);
    }

}
