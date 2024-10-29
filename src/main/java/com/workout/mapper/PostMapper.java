package com.workout.mapper;

import com.workout.model.post.Post;
import com.workout.model.post.PostUpdateParam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper extends CrudMapper<Post> {

    void update(Long id, PostUpdateParam updateParam);
}
