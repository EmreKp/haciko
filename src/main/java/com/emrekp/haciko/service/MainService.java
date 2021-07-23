package com.emrekp.haciko.service;

import com.emrekp.haciko.entity.Member;
import com.emrekp.haciko.entity.Post;
import com.emrekp.haciko.repo.MemberRepository;
import com.emrekp.haciko.repo.PostRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public MainService(
            @Autowired PostRepository postRepository,
            @Autowired MemberRepository memberRepository
    ) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public Iterable<Post> getPosts(Authentication auth) {
        List<Post> posts = postRepository.findAllPublicPosts();

        if (auth != null) {
            Member member = memberRepository.findByNick(auth.getName());

            posts.addAll(postRepository.findMemberPrivatePosts(member));
        }

        return posts.stream()
            .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
            .collect(Collectors.toList());
    }

    public void postMsg(Post post) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            Member member = memberRepository.findByNick(post.getNick().toLowerCase());

            if (member != null) {
                throw new RuntimeException("Nick sistemde mevcut. Başka bişey girin");
            }
        } else {
            post.setNick(auth.getName()); // TODO remove
            post.setMember(memberRepository.findByNick(post.getNick()));
        }

        postRepository.savePost(post);
    }

    public void registerMember(Member member) {
        memberRepository
                .save(member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword())
        ));
    }
}
