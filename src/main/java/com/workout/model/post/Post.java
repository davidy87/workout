package com.workout.model.post;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Post {

    private Long id;

    private String content;

    private Long memberId;
}
