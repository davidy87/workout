package com.workout.domain.answerlike.repository;

import com.workout.domain.answerlike.model.AnswerLike;

import java.util.List;

public interface AnswerLikeRepository {

    void save(AnswerLike answerLike);

    List<AnswerLike> findAllByAnswerId(Long answerId);

    void delete(Long id);
}
