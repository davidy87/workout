package com.workout.mapper;

import com.workout.model.post_comment.PostComment;
import com.workout.model.post_comment.PostCommentUpdateParam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostCommentMapper extends CrudMapper<PostComment> {

    void update(Long id, PostCommentUpdateParam updateParam);
}
