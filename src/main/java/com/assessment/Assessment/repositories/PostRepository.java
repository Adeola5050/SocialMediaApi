package com.assessment.Assessment.repositories;

import com.assessment.Assessment.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post,Long> {
    Page<Post> findAll(Pageable pageable);
}
