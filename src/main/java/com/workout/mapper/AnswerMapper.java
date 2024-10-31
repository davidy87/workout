package com.workout.mapper;

import com.workout.domain.answer.model.Answer;
import com.workout.domain.answer.model.AnswerUpdateParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnswerMapper extends CrudMapper<Answer> {

    List<Answer> findAllByQuestionId(Long questionId);

    List<Answer> findAllByMemberId(Long memberId);

    void update(Long id, AnswerUpdateParam updateParam);
}
