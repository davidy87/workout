package com.workout.domain.member.repository;

import com.workout.mapper.MemberMapper;
import com.workout.domain.member.model.Member;
import com.workout.domain.member.model.MemberUpdateParam;
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

    public int existByEmail(String email) {
        return memberMapper.existByEmail(email);
    }

    public int existByUsername(String username) {
        return memberMapper.existByUsername(username);
    }

    public void update(Long id, MemberUpdateParam updateParam) {
        memberMapper.update(id, updateParam);
    }

    public void delete(Long id) {
        memberMapper.delete(id);
    }
}
