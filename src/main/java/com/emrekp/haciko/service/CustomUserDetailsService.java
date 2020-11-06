package com.emrekp.haciko.service;

import com.emrekp.haciko.entity.Member;
import com.emrekp.haciko.security.MemberPrincipal;
import com.emrekp.haciko.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(@Autowired MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        Member member = memberRepository.findByNick(nick);

        if (member == null) {
            throw new UsernameNotFoundException("Nick not found");
        }

        return new MemberPrincipal(member);
    }
}
