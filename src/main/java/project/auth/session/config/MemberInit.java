package project.auth.session.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.auth.session.member.domain.Member;
import project.auth.session.member.repository.MemberRepository;

@Configuration
@RequiredArgsConstructor
public class MemberInit {
    private final MemberRepository memberRepository;

    @Bean
    public CommandLineRunner testData() {
        return args -> {
            if (memberRepository.findByLoginId("test").isEmpty()) {
                Member member = Member.builder()
                        .loginId("test")
                        .password("1234")
                        .name("테스트 사용자")
                        .build();
                memberRepository.save(member);
            }
        };
    }
}
