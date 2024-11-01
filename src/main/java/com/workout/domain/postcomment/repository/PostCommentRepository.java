package com.workout.domain.postcomment.repository;

import com.workout.domain.postcomment.model.PostComment;
import com.workout.domain.postcomment.model.PostCommentUpdateParam;

import java.util.List;
import java.util.Optional;

public interface PostCommentRepository {

    void save(PostComment postComment);

    List<PostComment> findAll();

    Optional<PostComment> findById(Long id);

    void update(Long id, PostCommentUpdateParam updateParam);

    void delete(Long id);
}
