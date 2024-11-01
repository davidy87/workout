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
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public void save(Member member) {
        memberMapper.save(member);
    }

    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberMapper.findById(id);
    }

    @Override
    public int existByEmail(String email) {
        return memberMapper.existByEmail(email);
    }

    @Override
    public int existByUsername(String username) {
        return memberMapper.existByUsername(username);
    }

    @Override
    public void update(Long id, MemberUpdateParam updateParam) {
        memberMapper.update(id, updateParam);
    }

    @Override
    public void delete(Long id) {
        memberMapper.delete(id);
    }
}
