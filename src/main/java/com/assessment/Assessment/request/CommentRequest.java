package com.assessment.Assessment.request;

import com.assessment.Assessment.model.User;
import lombok.Data;

@Data
public class CommentRequest {
    private User user;
    private String content;

}
