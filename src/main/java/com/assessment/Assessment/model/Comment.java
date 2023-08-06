package com.assessment.Assessment.model;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String content;


}
