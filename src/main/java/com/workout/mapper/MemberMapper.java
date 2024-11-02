package com.workout.mapper;

import com.workout.domain.member.model.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper extends CrudMapper<Member> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByUsername(String username);

    int existByEmail(String email);

    int existByUsername(String username);

    void updateUsername(Long id, String username);

    void updatePassword(Long id, String password);

    void updateProfileImage(Long id, String profileImage);
}
