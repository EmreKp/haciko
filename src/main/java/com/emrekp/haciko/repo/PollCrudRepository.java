package com.emrekp.haciko.repo;

import com.emrekp.haciko.entity.Poll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollCrudRepository extends CrudRepository<Poll, Long> {

}
