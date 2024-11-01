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
public class PostCommentRepositoryImpl implements PostCommentRepository {

    private final PostCommentMapper postCommentMapper;

    @Override
    public void save(PostComment postComment) {
        postCommentMapper.save(postComment);
    }

    @Override
    public List<PostComment> findAll() {
        return postCommentMapper.findAll();
    }

    @Override
    public Optional<PostComment> findById(Long id) {
        return postCommentMapper.findById(id);
    }

    @Override
    public void update(Long id, PostCommentUpdateParam updateParam) {
        postCommentMapper.update(id, updateParam);
    }

    @Override
    public void delete(Long id) {
        postCommentMapper.delete(id);
    }
}
