package com.emrekp.haciko.repo;

import com.emrekp.haciko.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCrudRepository extends CrudRepository<Member, Long> {
    Member findByNick(String nick);
}
