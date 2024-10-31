package com.workout.domain.answer.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Answer {

    private Long id;

    private String content;

    private Long questionId;

    private Long memberId;
}
