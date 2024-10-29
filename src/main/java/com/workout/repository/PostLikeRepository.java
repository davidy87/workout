package com.workout.repository;

import com.workout.mapper.PostLikeMapper;
import com.workout.model.post_like.PostLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PostLikeRepository {

    private final PostLikeMapper postLikeMapper;

    public void save(PostLike postLike) {
        postLikeMapper.save(postLike);
    }

    public List<PostLike> findByPostId(Long postId) {
        return postLikeMapper.findAllByPostId(postId);
    }

    public List<PostLike> findByMemberId(Long memberId) {
        return postLikeMapper.findAllByMemberId(memberId);
    }

    public void delete(Long id) {
        postLikeMapper.delete(id);
    }
}
