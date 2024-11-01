package com.workout.domain.member.repository;

import com.workout.domain.member.model.Member;
import com.workout.domain.member.model.MemberUpdateParam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("Member 저장 테스트")
    void save() {
        // given
        Member member = Member.builder()
                .email("user@gmail.com")
                .username("user")
                .password("testpass!")
                .profileImage("user.png")
                .build();

        // when
        memberRepository.save(member);

        // then
        List<Member> found = memberRepository.findAll();
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getEmail()).isEqualTo(member.getEmail());
        assertThat(found.get(0).getUsername()).isEqualTo(member.getUsername());
        assertThat(found.get(0).getPassword()).isEqualTo(member.getPassword());
        assertThat(found.get(0).getProfileImage()).isEqualTo(member.getProfileImage());
    }

    @Test
    @DisplayName("id별 Member 조회 테스트")
    void findById() {
        // given
        Member member = Member.builder()
                .email("user@gmail.com")
                .username("user")
                .password("testpass!")
                .profileImage("user.png")
                .build();

        memberRepository.save(member);

        // when
        Optional<Member> found = memberRepository.findById(member.getId());

        // then
        assertThat(found).isNotEmpty();
        assertThat(found.get().getEmail()).isEqualTo(member.getEmail());
        assertThat(found.get().getUsername()).isEqualTo(member.getUsername());
        assertThat(found.get().getPassword()).isEqualTo(member.getPassword());
        assertThat(found.get().getProfileImage()).isEqualTo(member.getProfileImage());
    }

    @Test
    @DisplayName("email별 Member 조회 테스트")
    void findByEmail() {
        // given
        Member member = Member.builder()
                .email("user@gmail.com")
                .username("user")
                .password("testpass!")
                .profileImage("user.png")
                .build();

        memberRepository.save(member);

        // when
        Optional<Member> found = memberRepository.findByEmail(member.getEmail());

        // then
        assertThat(found).isNotEmpty();
        assertThat(found.get().getEmail()).isEqualTo(member.getEmail());
        assertThat(found.get().getUsername()).isEqualTo(member.getUsername());
        assertThat(found.get().getPassword()).isEqualTo(member.getPassword());
        assertThat(found.get().getProfileImage()).isEqualTo(member.getProfileImage());
    }

    @Test
    @DisplayName("email로 Member 존재 여부 확인 테스트")
    void existByEmail() {
        // given
        Member member = Member.builder()
                .email("user@gmail.com")
                .username("user")
                .password("testpass!")
                .profileImage("user.png")
                .build();

        memberRepository.save(member);

        // when
        boolean result = memberRepository.existByEmail(member.getEmail());

        // then
        assertThat(result).isTrue();

    }

    @Test
    @DisplayName("username으로 Member 존재 여부 확인 테스트")
    void existByUsername() {
        // given
        Member member = Member.builder()
                .email("user@gmail.com")
                .username("user")
                .password("testpass!")
                .profileImage("user.png")
                .build();

        memberRepository.save(member);

        // when
        boolean result = memberRepository.existByUsername(member.getUsername());

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Member 수정 테스트")
    void update() {
        // given
        Member member = Member.builder()
                .email("user@gmail.com")
                .username("user")
                .password("testpass!")
                .profileImage("user.png")
                .build();

        memberRepository.save(member);

        // when
        MemberUpdateParam updateParam = MemberUpdateParam.builder()
                .email("newUser@gmail.com")
                .username("newUser")
                .password("newpass!")
                .profileImage("newUser.png")
                .build();

        memberRepository.update(member.getId(), updateParam);

        // then
        Optional<Member> found = memberRepository.findById(member.getId());
        assertThat(found).isNotEmpty();
        assertThat(found.get().getEmail()).isEqualTo(updateParam.getEmail());
        assertThat(found.get().getUsername()).isEqualTo(updateParam.getUsername());
        assertThat(found.get().getPassword()).isEqualTo(updateParam.getPassword());
        assertThat(found.get().getProfileImage()).isEqualTo(updateParam.getProfileImage());
    }

    @Test
    @DisplayName("Member 삭제 테스트")
    void delete() {
        // given
        Member member = Member.builder()
                .email("user@gmail.com")
                .username("user")
                .password("testpass!")
                .profileImage("user.png")
                .build();

        memberRepository.save(member);

        // when
        memberRepository.delete(member.getId());

        // then
        Optional<Member> found = memberRepository.findById(member.getId());
        assertThat(found).isEmpty();
    }
}