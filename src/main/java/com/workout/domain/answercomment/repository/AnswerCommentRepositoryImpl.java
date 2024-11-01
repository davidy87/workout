package com.workout.domain.answercomment.repository;

import com.workout.mapper.AnswerCommentMapper;
import com.workout.domain.answercomment.model.AnswerComment;
import com.workout.domain.answercomment.model.AnswerCommentUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AnswerCommentRepositoryImpl implements AnswerCommentRepository {

    private final AnswerCommentMapper answerCommentMapper;

    @Override
    public void save(AnswerComment answerComment) {
        answerCommentMapper.save(answerComment);
    }

    @Override
    public List<AnswerComment> findAllByAnswerId(Long answerId) {
        return answerCommentMapper.findAllByAnswerId(answerId);
    }

    @Override
    public List<AnswerComment> findAllByMemberId(Long memberId) {
        return answerCommentMapper.findAllByMemberId(memberId);
    }

    @Override
    public void update(Long id, AnswerCommentUpdateParam updateParam) {
        answerCommentMapper.update(id, updateParam);
    }

    @Override
    public void delete(Long id) {
        answerCommentMapper.delete(id);
    }
}
