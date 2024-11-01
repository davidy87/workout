package com.workout.mapper;

import com.workout.domain.member.model.Member;
import com.workout.domain.member.model.MemberUpdateParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper extends CrudMapper<Member> {

    Optional<Member> findByEmail(String email);

    int existByEmail(String email);

    int existByUsername(String username);

    void update(Long id, MemberUpdateParam updateParam);
}
