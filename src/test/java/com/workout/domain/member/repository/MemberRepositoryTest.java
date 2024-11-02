package com.workout.domain.member.repository;

import com.workout.domain.member.model.Member;
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
    @DisplayName("username별 Member 조회 테스트")
    void findByUsername() {
        // given
        Member member = Member.builder()
                .email("user@gmail.com")
                .username("user")
                .password("testpass!")
                .profileImage("user.png")
                .build();

        memberRepository.save(member);

        // when
        Optional<Member> found = memberRepository.findByUsername(member.getUsername());

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
    @DisplayName("Member username 수정 테스트")
    void updateUsername() {
        // given
        Member member = Member.builder()
                .email("user@gmail.com")
                .username("user")
                .password("testpass!")
                .profileImage("user.png")
                .build();

        memberRepository.save(member);

        // when
        String newUsername = "newUser";
        memberRepository.updateUsername(member.getId(), newUsername);

        // then
        Optional<Member> found = memberRepository.findById(member.getId());
        assertThat(found).isNotEmpty();
        assertThat(found.get().getEmail()).isEqualTo(member.getEmail());
        assertThat(found.get().getUsername()).isEqualTo(newUsername);
        assertThat(found.get().getPassword()).isEqualTo(member.getPassword());
        assertThat(found.get().getProfileImage()).isEqualTo(member.getProfileImage());
    }

    @Test
    @DisplayName("Member password 수정 테스트")
    void updatePassword() {
        // given
        Member member = Member.builder()
                .email("user@gmail.com")
                .username("user")
                .password("testpass!")
                .profileImage("user.png")
                .build();

        memberRepository.save(member);

        // when
        String newPassword = "newTestPass!";
        memberRepository.updatePassword(member.getId(), newPassword);

        // then
        Optional<Member> found = memberRepository.findById(member.getId());
        assertThat(found).isNotEmpty();
        assertThat(found.get().getEmail()).isEqualTo(member.getEmail());
        assertThat(found.get().getUsername()).isEqualTo(member.getUsername());
        assertThat(found.get().getPassword()).isEqualTo(newPassword);
        assertThat(found.get().getProfileImage()).isEqualTo(member.getProfileImage());
    }

    @Test
    @DisplayName("Member profileImage 수정 테스트")
    void updateProfileImage() {
        // given
        Member member = Member.builder()
                .email("user@gmail.com")
                .username("user")
                .password("testpass!")
                .profileImage("user.png")
                .build();

        memberRepository.save(member);

        // when
        String newProfileImage = "newUser.png";
        memberRepository.updateProfileImage(member.getId(), newProfileImage);

        // then
        Optional<Member> found = memberRepository.findById(member.getId());
        assertThat(found).isNotEmpty();
        assertThat(found.get().getEmail()).isEqualTo(member.getEmail());
        assertThat(found.get().getUsername()).isEqualTo(member.getUsername());
        assertThat(found.get().getPassword()).isEqualTo(member.getPassword());
        assertThat(found.get().getProfileImage()).isEqualTo(newProfileImage);
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