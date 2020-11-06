package com.emrekp.haciko.repo;

import com.emrekp.haciko.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {

    private final PostCrudRepository crudRepository;

    @Autowired
    public PostRepository(PostCrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    public Iterable<Post> findAll() {
        return crudRepository.findAll();
    }

    public void savePost(Post post) {
        crudRepository.save(post);
    }
}
