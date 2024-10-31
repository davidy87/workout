package com.workout.mapper;

import com.workout.model.answercomment.AnswerComment;
import com.workout.model.answercomment.AnswerCommentUpdateParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnswerCommentMapper extends CrudMapper<AnswerComment> {

    List<AnswerComment> findAllByAnswerId(Long answerId);

    List<AnswerComment> findAllByMemberId(Long memberId);

    void update(Long id, AnswerCommentUpdateParam updateParam);
}
