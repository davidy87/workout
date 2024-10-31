package com.workout.domain.postcomment.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostComment {

    private Long id;

    private String content;

    private Long postId;

    private Long memberId;
}
