package com.assessment.Assessment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    private String email;
    private String profilePicture;
    private String password;
    private Boolean isDeleted;
    @OneToMany(
            mappedBy = "id",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    private List<Follower> followers;
    private LocalDateTime modifiedDate;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private String authorities;

    public String getAuthorities() {
        return authorities;
    }
}

