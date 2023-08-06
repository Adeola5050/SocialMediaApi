package com.assessment.Assessment.controller;

import com.assessment.Assessment.exception.SocialMediaAppException;
import com.assessment.Assessment.model.Post;
import com.assessment.Assessment.model.User;
import com.assessment.Assessment.request.CommentRequest;
import com.assessment.Assessment.request.CreatePostDto;
import com.assessment.Assessment.request.UpdatePostDto;
import com.assessment.Assessment.response.PaginatedPostResponse;
import com.assessment.Assessment.response.ResponseDetails;
import com.assessment.Assessment.service.CommentService;
import com.assessment.Assessment.service.LikeService;
import com.assessment.Assessment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final LikeService likeService;
    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService, LikeService likeService, CommentService commentService) {
        this.postService = postService;
        this.likeService = likeService;
        this.commentService = commentService;
    }


    @PostMapping("/{postId}/likes")
    public ResponseEntity<?> likePost(@PathVariable Long postId, @RequestBody User user) throws SocialMediaAppException {
        Post post = postService.getPostById(postId);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        likeService.likePost(user, post);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<?> addComment(@PathVariable Long postId, @RequestBody CommentRequest commentRequest) throws SocialMediaAppException {
        Post post = postService.getPostById(postId);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        commentService.addComment(commentRequest.getUser(), post, commentRequest.getContent());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/createPost")
    public ResponseEntity<?> createPost(@RequestBody CreatePostDto dto) throws SocialMediaAppException {
        postService.createPost(dto);
        ResponseDetails responseDetails = postService.createPost(dto);
        return new ResponseEntity<>(responseDetails, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<PaginatedPostResponse> getAllPost(@RequestParam(required = false, defaultValue = "1") int page,
                                                            @RequestParam(required = false, defaultValue = "1") int limit,
                                                            @RequestParam(required = false) String name) {
        PaginatedPostResponse paginatedPostResponse = postService.findAllPost(page, limit, name);
        return new ResponseEntity<>(paginatedPostResponse, HttpStatus.OK);

    }

    @PutMapping("updatePost")
    public ResponseEntity<?> updatePost(@RequestBody UpdatePostDto postDto) throws SocialMediaAppException {
        if (postDto.getPostId() == null) {
            throw new SocialMediaAppException("Post id cannot be null");
        }
        postService.updatePost(postDto);
        return ResponseEntity.ok("Updated successfully");
    }
    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId) throws SocialMediaAppException{
        if(postId == null){
            throw new SocialMediaAppException("Id cannot be null");
        }
        else {
            return ResponseEntity.ok("post retrieved");
        }
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deleteById(@PathVariable Long postId) throws SocialMediaAppException{
        postService.deleteById(postId);
        return ResponseEntity.ok("Deleted successfully");
    }

}
