package com.emrekp.haciko.repo;

import com.emrekp.haciko.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    private final MemberCrudRepository crudRepository;

    public MemberRepository(@Autowired MemberCrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    public Member findByNick(String nick) {
        return crudRepository.findByNick(nick);
    }

    public void save(Member member) {
        crudRepository.save(member);
    }
}
