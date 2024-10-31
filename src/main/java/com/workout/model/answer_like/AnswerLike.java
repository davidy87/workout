package com.workout.model.answer_like;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnswerLike {

    private Long id;

    private Long answerId;

    private Long memberId;
}
