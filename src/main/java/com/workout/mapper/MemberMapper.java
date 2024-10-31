package com.workout.mapper;

import com.workout.domain.member.model.Member;
import com.workout.domain.member.model.MemberUpdateParam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper extends CrudMapper<Member> {

    void update(Long id, MemberUpdateParam updateParam);
}
