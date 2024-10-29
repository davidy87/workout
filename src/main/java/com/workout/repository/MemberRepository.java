package com.workout.repository;

import com.workout.mapper.MemberMapper;
import com.workout.model.member.Member;
import com.workout.model.member.MemberUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberRepository  {

    private final MemberMapper memberMapper;

    public void save(Member member) {
        memberMapper.save(member);
    }

    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    public Optional<Member> findById(Long id) {
        return memberMapper.findById(id);
    }

    public void update(Long id, MemberUpdateParam updateParam) {
        memberMapper.update(id, updateParam);
    }

    public void delete(Long id) {
        memberMapper.delete(id);
    }
}
