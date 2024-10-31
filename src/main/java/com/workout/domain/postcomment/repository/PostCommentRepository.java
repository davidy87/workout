package com.workout.domain.postcomment.repository;

import com.workout.mapper.PostCommentMapper;
import com.workout.domain.postcomment.model.PostComment;
import com.workout.domain.postcomment.model.PostCommentUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PostCommentRepository {

    private final PostCommentMapper postCommentMapper;

    public void save(PostComment postComment) {
        postCommentMapper.save(postComment);
    }

    public List<PostComment> findAll() {
        return postCommentMapper.findAll();
    }

    public Optional<PostComment> findById(Long id) {
        return postCommentMapper.findById(id);
    }

    public void update(Long id, PostCommentUpdateParam updateParam) {
        postCommentMapper.update(id, updateParam);
    }

    public void delete(Long id) {
        postCommentMapper.delete(id);
    }
}