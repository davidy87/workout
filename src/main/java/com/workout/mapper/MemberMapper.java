package com.workout.mapper;

import com.workout.model.member.Member;
import com.workout.model.member.MemberUpdateParam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper extends CrudMapper<Member> {

    void update(Long id, MemberUpdateParam updateParam);
}
