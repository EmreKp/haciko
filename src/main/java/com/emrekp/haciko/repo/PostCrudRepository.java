package com.emrekp.haciko.repo;

import com.emrekp.haciko.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCrudRepository extends CrudRepository<Post, Long> {
}
