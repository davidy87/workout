package com.workout.mapper;

import com.workout.model.post.Post;
import com.workout.model.post.PostUpdateParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper extends CrudMapper<Post> {

    List<Post> findAllByMemberId(Long memberId);

    void update(Long id, PostUpdateParam updateParam);
}
