package com.workout.mapper;

import com.workout.model.post_like.PostLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostLikeMapper extends CrudMapper<PostLike> {

    List<PostLike> findAllByPostId(Long postId);

    List<PostLike> findAllByMemberId(Long memberId);
}