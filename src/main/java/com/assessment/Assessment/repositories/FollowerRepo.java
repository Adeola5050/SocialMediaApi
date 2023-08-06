package com.assessment.Assessment.repositories;

import com.assessment.Assessment.model.Follower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FollowerRepo extends CrudRepository<Follower, Long> {
}
