package com.assessment.Assessment.service;

import com.assessment.Assessment.exception.SocialMediaAppException;
import com.assessment.Assessment.model.Post;
import com.assessment.Assessment.model.User;
import com.assessment.Assessment.repositories.PostRepository;
import com.assessment.Assessment.request.CreatePostDto;
import com.assessment.Assessment.request.UpdatePostDto;
import com.assessment.Assessment.response.PaginatedPostResponse;
import com.assessment.Assessment.response.ResponseDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public ResponseDetails createPost(CreatePostDto postDto) throws SocialMediaAppException {
        Post newpost = new Post();
        User userPost = new User();
        newpost.setContent(postDto.getContent());
        newpost.setCreationDate(LocalDateTime.now());
        newpost.setLikesCount(0);
        newpost.setUser(userPost);
        newpost.setComments(newpost.getComments());
        postRepository.save(newpost);
        return createdPostResponse();
    }

    @Override
    public List<Post> findAll() {
        return (List<Post>) postRepository.findAll();
    }

    @Override
    public void updatePost(UpdatePostDto dto) throws SocialMediaAppException {
        Post updatedPost = getPostById(dto.getPostId());
        if (dto.getContent() != null) {
            updatedPost.setContent(dto.getContent());
        }
        int likesCount = (updatedPost.getLikesCount());
        updatedPost.setLikesCount(likesCount);
        updatedPost.setModifiedDate(LocalDateTime.now());
        postRepository.save(updatedPost);
    }

    @Override
    public PaginatedPostResponse findAllPost(Integer pageNumber, Integer pageSize, String name) {
        int likesCount = 0;
        PaginatedPostResponse response = new PaginatedPostResponse();
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(order));
        List<Post> list = (List<Post>) postRepository.findAll();
        for (Post post : list) {
            likesCount = likesCount + (post.getLikesCount());
            if (pageNumber >= 1 && pageSize >= 1 && name != null) {
                Page<Post> posts = postRepository.findAll(pageable);
                response.setPosts(posts.toList());
                response.setNoOfTotalPages(posts.getTotalPages());
            } else {
                Page<Post> posts = postRepository.findAll(pageable);
                response.setPosts(posts.toList());
                response.setNoOfTotalPages(posts.getTotalPages());
            }
            response.setPageSize(pageSize);
            response.setCurrentPage(pageNumber);
            response.setNoOfPosts(list.size());
            response.setLikesCount(BigDecimal.valueOf(likesCount));
        }
        return response;
    }
    @Override
    public Post getPostById(Long postId) throws SocialMediaAppException {
        return postRepository.findById(postId).orElseThrow(() ->
                new SocialMediaAppException("This post does not exist"));
    }

    @Override
    public void deleteById(Long postId) throws SocialMediaAppException {
        postRepository.deleteById(postId);
    }


    private ResponseDetails createdPostResponse() throws SocialMediaAppException {
        ResponseDetails response = new ResponseDetails();
        response.setTimestamp(LocalDateTime.now());
        response.setMessage("Posting was successful");
        response.setStatus("success");
        return response;
    }

}
