package com.workout.domain.answercomment.model;

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
