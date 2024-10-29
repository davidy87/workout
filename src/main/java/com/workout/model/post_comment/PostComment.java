package com.workout.model.post_comment;

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
