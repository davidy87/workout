package com.workout.model.question;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Question {

    private Long id;

    private String content;

    private Long memberId;
}
