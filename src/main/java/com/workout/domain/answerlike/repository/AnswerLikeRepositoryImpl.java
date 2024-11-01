package com.workout.domain.answerlike.repository;

import com.workout.mapper.AnswerLikeMapper;
import com.workout.domain.answerlike.model.AnswerLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AnswerLikeRepositoryImpl implements AnswerLikeRepository {

    private final AnswerLikeMapper answerLikeMapper;

    @Override
    public void save(AnswerLike answerLike) {
        answerLikeMapper.save(answerLike);
    }

    @Override
    public List<AnswerLike> findAllByAnswerId(Long answerId) {
        return answerLikeMapper.findAllByAnswerId(answerId);
    }

    @Override
    public void delete(Long id) {
        answerLikeMapper.delete(id);
    }
}
