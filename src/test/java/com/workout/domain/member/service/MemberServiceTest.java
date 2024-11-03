package com.workout.domain.member.service;

import com.workout.domain.member.dto.*;
import com.workout.domain.member.exception.InvalidMemberException;
import com.workout.domain.member.model.Member;
import com.workout.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.workout.domain.member.exception.errorcode.MemberErrorCode.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    MemberService memberService;

    @Test
    @DisplayName("회원가입 정상 로직 테스트")
    void signUp() {
        // given
        String email = "user@gmail.com";
        String username = "user";
        String password = "testpass!";
        SignUpRequest signUpRequest = new SignUpRequest(email, username, password);

        // when
        SignUpResponse response = memberService.signUp(signUpRequest);

        // then
        Mockito.verify(memberRepository).existByEmail(email);
        Mockito.verify(memberRepository).existByUsername(username);
        Mockito.verify(passwordEncoder).encode(password);
        Mockito.verify(memberRepository).save(any(Member.class));

        assertThat(response.getEmail()).isEqualTo(signUpRequest.getEmail());
        assertThat(response.getUsername()).isEqualTo(signUpRequest.getUsername());
    }

    @Test
    @DisplayName("회원가입 예외 1 - 이메일 중복 시 예외 처리")
    void signUpWithDuplicateEmail() {
        // given
        String email = "user@gmail.com";
        String username = "user";
        String password = "testpass!";
        SignUpRequest signUpRequest = new SignUpRequest(email, username, password);

        Mockito.when(memberRepository.existByEmail(email))
                .thenReturn(true);

        // when & then
        assertThatThrownBy(() -> memberService.signUp(signUpRequest))
                .isInstanceOf(InvalidMemberException.class)
                .hasMessage(DUPLICATE_EMAIL.getMessage());
    }

    @Test
    @DisplayName("회원가입 예외 2 - 닉네임 중복 시 예외 처리")
    void signUpWithDuplicateUsername() {
        // given
        String email = "user@gmail.com";
        String username = "user";
        String password = "testpass!";
        SignUpRequest signUpRequest = new SignUpRequest(email, username, password);

        Mockito.when(memberRepository.existByUsername(username))
                .thenReturn(true);

        // when & then
        assertThatThrownBy(() -> memberService.signUp(signUpRequest))
                .isInstanceOf(InvalidMemberException.class)
                .hasMessage(DUPLICATE_USERNAME.getMessage());
    }

    @Test
    @DisplayName("로그인 정상 로직 테스트")
    void login() {
        // given
        String email = "user@gmail.com";
        String username = "user";
        String password = "testpass!";
        String encodedPassword = "encodedPassword";
        Member existingMember = Member.builder()
                .email(email)
                .username(username)
                .password(encodedPassword)
                .build();

        LoginRequest loginRequest = new LoginRequest(email, password);

        Mockito.when(memberRepository.findByEmail(email))
                .thenReturn(Optional.ofNullable(existingMember));

        Mockito.when(passwordEncoder.encode(password))
                .thenReturn(encodedPassword);

        // when
        LoginResponse response = memberService.login(loginRequest);

        // then
        Mockito.verify(memberRepository).findByEmail(email);
        Mockito.verify(passwordEncoder).encode(password);

        assertThat(response.getEmail()).isEqualTo(email);
        assertThat(response.getUsername()).isEqualTo(username);
    }

    @Test
    @DisplayName("로그인 예외 1 - 올바르지 않은 이메일 전달 시 예외 처리")
    void loginWithIncorrectEmail() {
        // given
        String incorrectEmail = "incorrectUser@gmail.com";
        String password = "testpass!";
        LoginRequest loginRequest = new LoginRequest(incorrectEmail, password);

        Mockito.when(memberRepository.findByEmail(incorrectEmail))
                .thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> memberService.login(loginRequest))
                .isInstanceOf(InvalidMemberException.class)
                .hasMessage(INCORRECT_EMAIL.getMessage());
    }

    @Test
    @DisplayName("로그인 예외 2 - 올바르지 않은 비밀번호 전달 시 예외 처리")
    void loginWithIncorrectPassword() {
        // given
        String email = "user@gmail.com";
        String username = "user";
        String password = "testpass!";
        String encodedPassword = "encodedPassword";
        String incorrectEncodedPassword = "incorrectEncodedPassword";
        LoginRequest loginRequest = new LoginRequest(email, password);

        Member existingMember = Member.builder()
                .email(email)
                .username(username)
                .password(encodedPassword)
                .build();

        Mockito.when(memberRepository.findByEmail(email))
                .thenReturn(Optional.ofNullable(existingMember));

        Mockito.when(passwordEncoder.encode(password))
                .thenReturn(incorrectEncodedPassword);

        // when & then
        assertThatThrownBy(() -> memberService.login(loginRequest))
                .isInstanceOf(InvalidMemberException.class)
                .hasMessage(INCORRECT_PASSWORD.getMessage());

        Mockito.verify(memberRepository).findByEmail(email);
        Mockito.verify(passwordEncoder).encode(password);
    }

    @Test
    @DisplayName("회원 정보 수정 예외 - 정보를 수정할 회원이 존재하지 않을 경우 예외 처리")
    void updateWithIncorrectEmail() {
        // given
        String newPassword = "newPassword";
        UpdateRequest request = UpdateRequest.builder()
                .memberId(1L)
                .newPassword(newPassword)
                .build();

        Mockito.when(memberRepository.findById(request.getMemberId()))
                .thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> memberService.update(request))
                .isInstanceOf(InvalidMemberException.class)
                .hasMessage(MEMBER_NOT_EXIST.getMessage());

        Mockito.verify(memberRepository).findById(request.getMemberId());
    }

    @Test
    @DisplayName("회원 닉네임 수정 정상 로직 테스트")
    void updateUsername() {
        // given
        String email = "user@gmail.com";
        String currentUsername = "user";
        String password = "testpass!";
        Member member = Member.builder()
                .id(1L)
                .email(email)
                .username(currentUsername)
                .password(password)
                .build();

        String newUsername = "newUser";
        UpdateRequest request = UpdateRequest.builder()
                .memberId(member.getId())
                .newUsername(newUsername)
                .build();

        Mockito.when(memberRepository.findById(request.getMemberId()))
                .thenReturn(Optional.of(member));

        // when
        UpdateResponse response = memberService.update(request);

        // then
        Mockito.verify(memberRepository).findById(request.getMemberId());
        Mockito.verify(memberRepository).updateUsername(member.getId(), newUsername);
        assertThat(response.getMessage()).isEqualTo("success");
    }

    @Test
    @DisplayName("회원 닉네임 수정 예외 - 새로운 닉네임이 현재 닉네임과 같을 경우 예외 처리")
    void updateSameUsername() {
        // given
        String email = "user@gmail.com";
        String currentUsername = "user";
        Member member = Member.builder()
                .id(1L)
                .email(email)
                .username(currentUsername)
                .build();

        UpdateRequest request = UpdateRequest.builder()
                .memberId(member.getId())
                .newUsername(currentUsername)
                .build();

        Mockito.when(memberRepository.findById(request.getMemberId()))
                .thenReturn(Optional.of(member));

        // when & then
        assertThatThrownBy(() -> memberService.update(request))
                .isInstanceOf(InvalidMemberException.class)
                .hasMessage(INVALID_NEW_USERNAME.getMessage());

        Mockito.verify(memberRepository).findById(request.getMemberId());
    }

    @Test
    @DisplayName("회원 비밀번호 변경 정상 로직 테스트")
    void updatePassword() {
        // given
        String email = "user@gmail.com";
        String username = "user";
        String password = "testpass!";
        Member member = Member.builder()
                .id(1L)
                .email(email)
                .username(username)
                .password(password)
                .build();

        String newPassword = "newPassword";
        String encodedNewPassword = "encodedNewPassword";
        UpdateRequest request = UpdateRequest.builder()
                .memberId(member.getId())
                .newPassword(newPassword)
                .build();

        Mockito.when(memberRepository.findById(request.getMemberId()))
                .thenReturn(Optional.of(member));

        Mockito.when(passwordEncoder.encode(newPassword))
                .thenReturn(encodedNewPassword);

        // when
        UpdateResponse response = memberService.update(request);

        // then
        Mockito.verify(memberRepository).findById(request.getMemberId());
        Mockito.verify(passwordEncoder).encode(newPassword);
        Mockito.verify(memberRepository).updatePassword(member.getId(), encodedNewPassword);
        assertThat(response.getMessage()).isEqualTo("success");
    }

    @Test
    @DisplayName("회원 프로필 이미지 수정 정상 로직 테스트")
    void updateProfileImage() {
        // given
        String email = "user@gmail.com";
        String username = "user";
        String password = "testpass!";
        String profileImage = "user.png";
        Member member = Member.builder()
                .id(1L)
                .email(email)
                .username(username)
                .password(password)
                .profileImage(profileImage)
                .build();

        String newProfileImage = "newUser.png";
        UpdateRequest request = UpdateRequest.builder()
                .memberId(member.getId())
                .newProfileImage(newProfileImage)
                .build();

        Mockito.when(memberRepository.findById(request.getMemberId()))
                .thenReturn(Optional.of(member));

        // when
        UpdateResponse response = memberService.update(request);

        // then
        Mockito.verify(memberRepository).findById(request.getMemberId());
        Mockito.verify(memberRepository).updateProfileImage(member.getId(), newProfileImage);
        assertThat(response.getMessage()).isEqualTo("success");
    }

    @Test
    @DisplayName("회원 삭제 정상 로직 테스트")
    void delete() {
        // given
        String email = "user@gmail.com";
        String username = "user";
        String password = "testpass!";
        String profileImage = "user.png";
        Member member = Member.builder()
                .id(1L)
                .email(email)
                .username(username)
                .password(password)
                .profileImage(profileImage)
                .build();

        Mockito.when(memberRepository.findById(member.getId()))
                .thenReturn(Optional.of(member));

        // when
        DeleteResponse response = memberService.delete(member.getId());

        // then
        Mockito.verify(memberRepository).findById(member.getId());
        Mockito.verify(memberRepository).delete(member.getId());
        assertThat(response.getDeletedMember().getId()).isEqualTo(member.getId());
        assertThat(response.getDeletedMember().getEmail()).isEqualTo(member.getEmail());
        assertThat(response.getDeletedMember().getUsername()).isEqualTo(member.getUsername());
    }

    @Test
    @DisplayName("회원 삭제 예외 - 존재하지 않는 회원을 삭제하려는 경우 예외 처리")
    void deleteNotExistedMember() {
        // given
        Long invalidMemberId = 1L;
        Mockito.when(memberRepository.findById(any(Long.class)))
                .thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> memberService.delete(invalidMemberId))
                .isInstanceOf(InvalidMemberException.class)
                .hasMessage(MEMBER_NOT_EXIST.getMessage());

        Mockito.verify(memberRepository).findById(invalidMemberId);
    }
}