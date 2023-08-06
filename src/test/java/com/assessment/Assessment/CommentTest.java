package com.assessment.Assessment;

import com.assessment.Assessment.model.Comment;
import com.assessment.Assessment.model.Follower;
import com.assessment.Assessment.model.Post;
import com.assessment.Assessment.model.User;
import com.assessment.Assessment.repositories.CommentRepository;
import com.assessment.Assessment.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class CommentTest {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    CommentService commentService;

    @Autowired
    public CommentTest(CommentService commentService) {
        this.commentService = commentService;
    }

    @Test
    void testThatCreateComment() {
        Comment newComment = new Comment();
        Post post = new Post();
        post.setContent("reality show");
        post.setCreationDate(LocalDateTime.now());
        User user = new User();
        List<Follower> followerList = new ArrayList<>();
        Follower follower = new Follower();
        follower.setProfilePicture("image009");
        follower.setUsername("feranmi");
        followerList.add(follower);
        user.setUsername("ben");
        user.setEmail("benbruce@gmail.com");
        user.setPassword("22222222");
        user.setFollowers(followerList);
        user.setIsActive(true);
        newComment.setContent("Fun video");
        newComment.setUser(user);
        newComment.setPost(post);
        newComment.setCreatedDate(LocalDateTime.now());
        assertThat(newComment).isNotNull();
        log.info("{}-->", newComment);
    }

    @Test
    void testThatUpdateComment() {
        Comment updatedComment= new Comment();
        updatedComment.setModifiedDate(LocalDateTime.now());
        updatedComment.setContent("");
        assertThat(updatedComment).isNotNull();

    }

    @Test
    void testThatFindAllComments() {
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        List<Comment> comments = (List<Comment>) commentRepository.findAll();
        assertThat(comments.size());
        assertTrue(comments.contains(comment1));
        assertTrue(comments.contains(comment2));
    }

    @Test
    void testThatAddComment(){

    }
    @Test
    void testThatFindById(){
        Long commentId=8L;
        Optional<Comment> comment=commentRepository.findById(commentId);
        assertThat(comment).isNotNull();

    }
    @Test
    void testThatDeleteById(){
        Long commentId=9L;
        commentRepository.deleteById(commentId);
        assertThat(commentId).isNotNull();
    }
}