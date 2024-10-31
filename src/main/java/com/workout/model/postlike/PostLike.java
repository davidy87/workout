package com.workout.model.postlike;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostLike {

    private Long id;

    private Long postId;

    private Long memberId;
}
