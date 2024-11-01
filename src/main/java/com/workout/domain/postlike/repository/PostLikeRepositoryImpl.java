package com.workout.domain.postlike.repository;

import com.workout.mapper.PostLikeMapper;
import com.workout.domain.postlike.model.PostLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PostLikeRepositoryImpl implements PostLikeRepository {

    private final PostLikeMapper postLikeMapper;

    @Override
    public void save(PostLike postLike) {
        postLikeMapper.save(postLike);
    }

    @Override
    public List<PostLike> findByPostId(Long postId) {
        return postLikeMapper.findAllByPostId(postId);
    }

    @Override
    public List<PostLike> findByMemberId(Long memberId) {
        return postLikeMapper.findAllByMemberId(memberId);
    }

    @Override
    public void delete(Long id) {
        postLikeMapper.delete(id);
    }
}
