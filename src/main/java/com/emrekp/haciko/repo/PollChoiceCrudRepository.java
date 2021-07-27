package com.emrekp.haciko.repo;

import com.emrekp.haciko.entity.PollChoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollChoiceCrudRepository extends CrudRepository<PollChoice, Long> {

}
