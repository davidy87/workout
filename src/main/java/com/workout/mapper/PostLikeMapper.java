package com.workout.mapper;

import com.workout.domain.postlike.model.PostLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostLikeMapper extends CrudMapper<PostLike> {

    List<PostLike> findAllByPostId(Long postId);

    List<PostLike> findAllByMemberId(Long memberId);
}
