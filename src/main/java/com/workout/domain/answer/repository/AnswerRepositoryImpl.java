package com.workout.domain.answer.repository;

import com.workout.mapper.AnswerMapper;
import com.workout.domain.answer.model.Answer;
import com.workout.domain.answer.model.AnswerUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AnswerRepositoryImpl implements AnswerRepository {

    private final AnswerMapper answerMapper;

    @Override
    public void save(Answer answer) {
        answerMapper.save(answer);
    }

    @Override
    public List<Answer> findAllByQuestionId(Long questionId) {
        return answerMapper.findAllByQuestionId(questionId);
    }

    @Override
    public List<Answer> findAllByMemberId(Long memberId) {
        return answerMapper.findAllByMemberId(memberId);
    }

    @Override
    public void update(Long id, AnswerUpdateParam updateParam) {
        answerMapper.update(id, updateParam);
    }

    @Override
    public void delete(Long id) {
        answerMapper.delete(id);
    }
}
