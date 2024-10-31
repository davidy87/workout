package com.workout.model.answer_comment;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnswerComment {

    private Long id;

    private String content;

    private Long answerId;

    private Long memberId;
}
