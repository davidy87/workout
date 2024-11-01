package com.workout.domain.question.repository;

import com.workout.domain.question.model.Question;
import com.workout.domain.question.model.QuestionUpdateParam;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {

    void save(Question question);

    List<Question> findAll();

    List<Question> findAllByMemberId(Long memberId);

    Optional<Question> findById(Long id);

    void update(Long id, QuestionUpdateParam updateParam);

    void delete(Long id);
}
