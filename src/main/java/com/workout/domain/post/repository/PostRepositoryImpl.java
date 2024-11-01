package com.workout.domain.post.repository;

import com.workout.mapper.PostMapper;
import com.workout.domain.post.model.Post;
import com.workout.domain.post.model.PostUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PostRepositoryImpl implements PostRepository {

    private final PostMapper postMapper;

    @Override
    public void save(Post post) {
        postMapper.save(post);
    }

    @Override
    public List<Post> findAll() {
        return postMapper.findAll();
    }

    @Override
    public List<Post> findAllByMemberId(Long memberId) {
        return postMapper.findAllByMemberId(memberId);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postMapper.findById(id);
    }

    @Override
    public void update(Long id, PostUpdateParam updateParam) {
        postMapper.update(id, updateParam);
    }

    @Override
    public void delete(Long id) {
        postMapper.delete(id);
    }
}
