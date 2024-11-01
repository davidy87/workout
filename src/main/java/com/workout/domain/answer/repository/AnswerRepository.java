package com.workout.domain.answer.repository;

import com.workout.domain.answer.model.Answer;
import com.workout.domain.answer.model.AnswerUpdateParam;

import java.util.List;

public interface AnswerRepository {

    void save(Answer answer);

    List<Answer> findAllByQuestionId(Long questionId);

    List<Answer> findAllByMemberId(Long memberId);

    void update(Long id, AnswerUpdateParam updateParam);

    void delete(Long id);
}
