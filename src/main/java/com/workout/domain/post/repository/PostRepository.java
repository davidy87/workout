package com.workout.domain.post.repository;

import com.workout.domain.post.model.Post;
import com.workout.domain.post.model.PostUpdateParam;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    void save(Post post);

    List<Post> findAll();

    List<Post> findAllByMemberId(Long memberId);

    Optional<Post> findById(Long id);

    void update(Long id, PostUpdateParam updateParam);

    void delete(Long id);
}
