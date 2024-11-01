package com.workout.domain.member.repository;

import com.workout.domain.member.model.Member;
import com.workout.domain.member.model.MemberUpdateParam;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    void save(Member member);

    List<Member> findAll();

    Optional<Member> findById(Long id);

    Optional<Member> findByEmail(String email);

    boolean existByEmail(String email);

    boolean existByUsername(String username);

    void update(Long id, MemberUpdateParam updateParam);

    void delete(Long id);
}
