package com.workout.repository;

import com.workout.mapper.AnswerCommentMapper;
import com.workout.model.answer_comment.AnswerComment;
import com.workout.model.answer_comment.AnswerCommentUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AnswerCommentRepository {

    private final AnswerCommentMapper answerCommentMapper;

    public void save(AnswerComment answerComment) {
        answerCommentMapper.save(answerComment);
    }

    public List<AnswerComment> findAllByAnswerId(Long answerId) {
        return answerCommentMapper.findAllByAnswerId(answerId);
    }

    public List<AnswerComment> findAllByMemberId(Long memberId) {
        return answerCommentMapper.findAllByMemberId(memberId);
    }

    public void update(Long id, AnswerCommentUpdateParam updateParam) {
        answerCommentMapper.update(id, updateParam);
    }

    public void delete(Long id) {
        answerCommentMapper.delete(id);
    }
}
