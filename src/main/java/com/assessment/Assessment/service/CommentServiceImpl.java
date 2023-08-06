package com.assessment.Assessment.service;

import com.assessment.Assessment.exception.SocialMediaAppException;
import com.assessment.Assessment.model.Comment;
import com.assessment.Assessment.model.Post;
import com.assessment.Assessment.model.User;
import com.assessment.Assessment.repositories.CommentRepository;
import com.assessment.Assessment.request.CreateCommentDto;
import com.assessment.Assessment.request.UpdateCommentDto;
import com.assessment.Assessment.response.ResponseDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;


    @Override
    public ResponseDetails createComment(User user, Post post, CreateCommentDto dto) throws SocialMediaAppException {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(dto.getContent());
        comment.setCreatedDate(LocalDateTime.now());
        commentRepository.save(comment);
        return newComment();
    }

    @Override
    public void updateComment(UpdateCommentDto dto) throws SocialMediaAppException {
        Comment updateComment = getCommentById(dto.getCommentId());
        if (dto.getContent() != null) {
            updateComment.setContent(dto.getContent());
        }
        updateComment.setModifiedDate(LocalDateTime.now());
        commentRepository.save(updateComment);

    }

    private ResponseDetails newComment() {
        ResponseDetails response = new ResponseDetails();
        response.setTimestamp(LocalDateTime.now());
        response.setMessage("comment posted successfully");
        response.setStatus("status");
        return response;
    }


    @Override
    public List<Comment> findAll() {
        return (List<Comment>) commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long commentId) throws SocialMediaAppException {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new SocialMediaAppException("Comment with this does not exist in the database"));
    }

    @Override
    public void addComment(User user, Post post, String content) {
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(content);
        commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long commentId) throws SocialMediaAppException {
        commentRepository.deleteById(commentId);
    }


}
