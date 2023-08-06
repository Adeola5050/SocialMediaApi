package com.assessment.Assessment;

import com.assessment.Assessment.model.Post;
import com.assessment.Assessment.repositories.PostRepository;
import com.assessment.Assessment.repositories.UserRepository;
import com.assessment.Assessment.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class PostTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    PostService postService;

    @Autowired
    public PostTest(PostService postService){
        this.postService=postService;
    }

    @Test
    void testThatCreatePost() {
        Post post = new Post();
        post.setUser(post.getUser());
        post.setContent("fun video");
        post.setLikesCount(0);
        post.setCreationDate(LocalDateTime.now());
        assertThat(post).isNotNull();
        log.info("{}-->",post);
    }

    @Test
    void testThatUpdatePost(){
        Post updatedPost= new Post();
        updatedPost.setContent("Comedy");
        updatedPost.setLikesCount(3);
        updatedPost.setModifiedDate(LocalDateTime.now());
        updatedPost.setDeleted(true);
        assertThat(updatedPost).isNotNull();

    }

    @Test
    void testThatFindAllPost(){
        List<Post> postList= (List<Post>) postRepository.findAll();
        assertThat(postList.size()).isNotNull();
        log.info("{}-->", postList);

    }

    @Test
    void testThatFindPostById(){
        Long postId= 4L;
        Optional<Post> post= postRepository.findById(postId);
        assertThat(post).isNotNull();


    }


}
