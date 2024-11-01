package com.workout.domain.answercomment.repository;

import com.workout.domain.answercomment.model.AnswerComment;
import com.workout.domain.answercomment.model.AnswerCommentUpdateParam;

import java.util.List;

public interface AnswerCommentRepository {

    void save(AnswerComment answerComment);

    List<AnswerComment> findAllByAnswerId(Long answerId);

    List<AnswerComment> findAllByMemberId(Long memberId);

    void update(Long id, AnswerCommentUpdateParam updateParam);

    void delete(Long id);
}
