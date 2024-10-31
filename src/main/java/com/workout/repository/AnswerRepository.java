package com.workout.repository;

import com.workout.mapper.AnswerMapper;
import com.workout.model.answer.Answer;
import com.workout.model.answer.AnswerUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AnswerRepository {

    private final AnswerMapper answerMapper;

    public void save(Answer answer) {
        answerMapper.save(answer);
    }

    public List<Answer> findAllByQuestionId(Long questionId) {
        return answerMapper.findAllByQuestionId(questionId);
    }

    public List<Answer> findAllByMemberId(Long memberId) {
        return answerMapper.findAllByMemberId(memberId);
    }

    public void update(Long id, AnswerUpdateParam updateParam) {
        answerMapper.update(id, updateParam);
    }

    public void delete(Long id) {
        answerMapper.delete(id);
    }
}
