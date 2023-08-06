package com.assessment.Assessment.request;

import lombok.Data;

@Data
public class UpdatePostDto {
    private Long postId;
    private String content;
}
