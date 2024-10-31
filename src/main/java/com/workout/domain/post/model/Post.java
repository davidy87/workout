package com.workout.domain.post.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Post {

    private Long id;

    private String content;

    private Long memberId;
}
