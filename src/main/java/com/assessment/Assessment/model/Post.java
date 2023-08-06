package com.assessment.Assessment.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String content;
    private LocalDateTime creationDate;
    private int likesCount;
    @OneToOne
            (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private User user;
    @OneToMany(
            mappedBy = "id",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    private List<Comment> comments;
    private boolean isDeleted;
    private LocalDateTime modifiedDate;

}
