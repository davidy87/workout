package com.workout.mapper;

import com.workout.domain.answercomment.model.AnswerComment;
import com.workout.domain.answercomment.model.AnswerCommentUpdateParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnswerCommentMapper extends CrudMapper<AnswerComment> {

    List<AnswerComment> findAllByAnswerId(Long answerId);

    List<AnswerComment> findAllByMemberId(Long memberId);

    void update(Long id, AnswerCommentUpdateParam updateParam);
}
