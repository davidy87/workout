package com.workout.domain.postlike.repository;

import com.workout.domain.postlike.model.PostLike;

import java.util.List;

public interface PostLikeRepository {

    void save(PostLike postLike);

    List<PostLike> findByPostId(Long postId);

    List<PostLike> findByMemberId(Long memberId);

    void delete(Long id);
}
