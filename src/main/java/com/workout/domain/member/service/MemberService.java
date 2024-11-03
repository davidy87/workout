package com.workout.domain.member.service;

import com.workout.domain.member.dto.*;
import com.workout.domain.member.exception.InvalidMemberException;
import com.workout.domain.member.model.Member;
import com.workout.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import static com.workout.domain.member.exception.errorcode.MemberErrorCode.*;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        String email = signUpRequest.getEmail();
        String username = signUpRequest.getUsername();

        if (memberRepository.existByEmail(email)) {
            throw new InvalidMemberException(DUPLICATE_EMAIL);
        }

        if (memberRepository.existByUsername(username)) {
            throw new InvalidMemberException(DUPLICATE_USERNAME);
        }

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        Member newMember = Member.builder()
                .email(email)
                .username(username)
                .password(encodedPassword)
                .build();

        memberRepository.save(newMember);

        return new SignUpResponse(email, username);
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidMemberException(INCORRECT_EMAIL));

        String encodedPassword = passwordEncoder.encode(loginRequest.getPassword());

        if (!encodedPassword.equals(member.getPassword())) {
            throw new InvalidMemberException(INCORRECT_PASSWORD);
        }

        return new LoginResponse(email, member.getUsername());
    }

    public UpdateResponse update(UpdateRequest updateRequest) {
        Member member = memberRepository.findById(updateRequest.getMemberId())
                .orElseThrow(() -> new InvalidMemberException(MEMBER_NOT_EXIST));

        String newUsername = updateRequest.getNewUsername();
        String newPassword = updateRequest.getNewPassword();
        String newProfileImage = updateRequest.getNewProfileImage();

        if (StringUtils.hasText(newUsername)) {
            if (newUsername.equals(member.getUsername())) {
                throw new InvalidMemberException(INVALID_NEW_USERNAME);
            }

            memberRepository.updateUsername(member.getId(), newUsername);
        }

        if (StringUtils.hasText(newPassword)) {
            memberRepository.updatePassword(member.getId(), passwordEncoder.encode(newPassword));
        }

        if (StringUtils.hasText(newProfileImage)) {
            memberRepository.updateProfileImage(member.getId(), newProfileImage);
        }

        return new UpdateResponse("success");
    }

    public DeleteResponse delete(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new InvalidMemberException(MEMBER_NOT_EXIST));

        memberRepository.delete(id);

        return new DeleteResponse(member);
    }
}
