package com.assessment.Assessment.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    private String profilePicture;

}
