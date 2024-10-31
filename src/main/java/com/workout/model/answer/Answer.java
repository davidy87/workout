package com.workout.model.answer;

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
