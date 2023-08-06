package com.assessment.Assessment.request;

import lombok.Data;

@Data
public class UpdateCommentDto {
    private long commentId;
    private String content;
}
