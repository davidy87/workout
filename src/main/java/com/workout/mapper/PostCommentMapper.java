package com.workout.mapper;

import com.workout.domain.postcomment.model.PostComment;
import com.workout.domain.postcomment.model.PostCommentUpdateParam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostCommentMapper extends CrudMapper<PostComment> {

    void update(Long id, PostCommentUpdateParam updateParam);
}
