package com.emrekp.haciko.repo;

import com.emrekp.haciko.entity.Member;
import com.emrekp.haciko.entity.Post;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {

    private final PostCrudRepository crudRepository;

    @Autowired
    public PostRepository(PostCrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    public List<Post> findAllPublicPosts() {
        return crudRepository.findByIsPublicTrue();
    }

    public List<Post> findMemberPrivatePosts(Member member) {
        return crudRepository.findByIsPublicFalseAndMemberIs(member);
    }

    public void savePost(Post post) {
        crudRepository.save(post);
    }
}
