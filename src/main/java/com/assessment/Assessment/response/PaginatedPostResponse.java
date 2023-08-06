package com.assessment.Assessment.response;

import com.assessment.Assessment.model.Post;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PaginatedPostResponse {
    private List<Post> posts;
    private int currentPage;
    private int noOfPosts;
    private int noOfTotalPages;
    private int pageSize;
   private BigDecimal likesCount;

}
