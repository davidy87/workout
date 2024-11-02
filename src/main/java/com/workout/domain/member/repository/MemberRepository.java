package com.workout.domain.member.repository;

import com.workout.domain.member.model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    void save(Member member);

    List<Member> findAll();

    Optional<Member> findById(Long id);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByUsername(String username);

    boolean existByEmail(String email);

    boolean existByUsername(String username);

    void updateUsername(Long id, String username);

    void updatePassword(Long id, String password);

    void updateProfileImage(Long id, String profileImage);

    void delete(Long id);
}
