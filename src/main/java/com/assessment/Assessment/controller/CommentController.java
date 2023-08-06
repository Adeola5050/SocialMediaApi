package com.assessment.Assessment.controller;

import com.assessment.Assessment.exception.SocialMediaAppException;
import com.assessment.Assessment.model.Comment;
import com.assessment.Assessment.model.Post;
import com.assessment.Assessment.model.User;
import com.assessment.Assessment.request.CreateCommentDto;
import com.assessment.Assessment.request.UpdateCommentDto;
import com.assessment.Assessment.response.ResponseDetails;
import com.assessment.Assessment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/createComment")
    public ResponseEntity<?> createComment(@RequestBody User user, Post post, CreateCommentDto commentDto) throws SocialMediaAppException {
        commentService.createComment(user,post,commentDto);
        ResponseDetails response=commentService.createComment(user,post,commentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/updateComment")
    public ResponseEntity<?> updateComment(@RequestBody UpdateCommentDto updateComment) throws SocialMediaAppException{
        commentService.updateComment(updateComment);
        return ResponseEntity.ok("Updated Successfully");
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllComments(){
        List<Comment> comments= commentService.findAll();
        return ResponseEntity.ok(comments);
    }
    @GetMapping("/{commentId}")
    public ResponseEntity<?> getCommentById(@PathVariable Long commentId) throws SocialMediaAppException{
        if(commentId == null){
            throw new SocialMediaAppException("Id cannot be null");
        }
        commentService.getCommentById(commentId);
        return ResponseEntity.ok("Comment Retrieved");
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId)throws SocialMediaAppException{
        commentService.deleteById(commentId);
        return ResponseEntity.ok("deleted successfully");
    }

}
