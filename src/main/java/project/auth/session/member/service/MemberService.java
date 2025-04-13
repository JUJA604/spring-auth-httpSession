package project.auth.session.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.auth.session.member.domain.Member;
import project.auth.session.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }
}
