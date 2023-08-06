package com.assessment.Assessment.service;

import com.assessment.Assessment.exception.SocialMediaAppException;
import com.assessment.Assessment.model.Comment;
import com.assessment.Assessment.model.Post;
import com.assessment.Assessment.model.User;
import com.assessment.Assessment.request.CreateCommentDto;
import com.assessment.Assessment.request.UpdateCommentDto;
import com.assessment.Assessment.response.ResponseDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    ResponseDetails createComment(User user, Post post, CreateCommentDto dto) throws SocialMediaAppException;


    void updateComment(UpdateCommentDto dto) throws SocialMediaAppException;

    List<Comment> findAll();

    Comment getCommentById(Long id) throws SocialMediaAppException;

    void addComment(User user, Post post, String content);
    void deleteById(Long id) throws SocialMediaAppException;
}
