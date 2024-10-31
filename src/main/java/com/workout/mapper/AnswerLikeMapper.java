package com.workout.mapper;

import com.workout.model.answer_like.AnswerLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnswerLikeMapper extends CrudMapper<AnswerLike> {

    List<AnswerLike> findAllByAnswerId(Long answerId);
}
