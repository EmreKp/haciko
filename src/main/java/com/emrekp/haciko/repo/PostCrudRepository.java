package com.emrekp.haciko.repo;

import com.emrekp.haciko.entity.Member;
import com.emrekp.haciko.entity.Post;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCrudRepository extends CrudRepository<Post, Long> {
  List<Post> findByIsPublicTrue();
  List<Post> findByIsPublicFalseAndMemberIs(Member member);
}
