package com.assessment.Assessment.repositories;

import com.assessment.Assessment.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CommentRepository extends CrudRepository<Comment,Long> {

}
