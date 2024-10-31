package com.workout.model.postcomment;

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
