package com.assessment.Assessment.service;

import com.assessment.Assessment.exception.SocialMediaAppException;
import com.assessment.Assessment.model.Post;
import com.assessment.Assessment.request.CreatePostDto;
import com.assessment.Assessment.request.UpdatePostDto;
import com.assessment.Assessment.response.PaginatedPostResponse;
import com.assessment.Assessment.response.ResponseDetails;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface PostService {
    ResponseDetails createPost(CreatePostDto postDto) throws SocialMediaAppException;

     List<Post> findAll();

     void updatePost(UpdatePostDto dto) throws SocialMediaAppException;

     PaginatedPostResponse findAllPost(Integer pageNumber,
                                       Integer pageSize,
                                       String name);

    Post getPostById(Long postId) throws SocialMediaAppException;

    void deleteById(Long postId) throws SocialMediaAppException;
}
