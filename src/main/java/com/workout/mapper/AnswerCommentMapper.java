package com.workout.mapper;

import com.workout.model.answer_comment.AnswerComment;
import com.workout.model.answer_comment.AnswerCommentUpdateParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnswerCommentMapper extends CrudMapper<AnswerComment> {

    List<AnswerComment> findAllByAnswerId(Long answerId);

    List<AnswerComment> findAllByMemberId(Long memberId);

    void update(Long id, AnswerCommentUpdateParam updateParam);
}
