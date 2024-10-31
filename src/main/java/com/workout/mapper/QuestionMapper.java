package com.workout.mapper;

import com.workout.domain.question.model.Question;
import com.workout.domain.question.model.QuestionUpdateParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper extends CrudMapper<Question> {

    List<Question> findAllByMemberId(Long memberId);

    void update(Long id, QuestionUpdateParam updateParam);
}
