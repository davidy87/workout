package com.workout.repository;

import com.workout.mapper.AnswerLikeMapper;
import com.workout.model.answerlike.AnswerLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AnswerLikeRepository {

    private final AnswerLikeMapper answerLikeMapper;

    public void save(AnswerLike answerLike) {
        answerLikeMapper.save(answerLike);
    }

    public List<AnswerLike> findAllByAnswerId(Long answerId) {
        return answerLikeMapper.findAllByAnswerId(answerId);
    }

    public void delete(Long id) {
        answerLikeMapper.delete(id);
    }
}
