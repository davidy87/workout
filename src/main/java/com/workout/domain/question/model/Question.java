package com.workout.domain.question.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Question {

    private Long id;

    private String content;

    private Long memberId;
}
