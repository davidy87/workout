package com.workout.mapper;

import com.workout.model.question.Question;
import com.workout.model.question.QuestionUpdateParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper extends CrudMapper<Question> {

    List<Question> findAllByMemberId(Long memberId);

    void update(Long id, QuestionUpdateParam updateParam);
}
