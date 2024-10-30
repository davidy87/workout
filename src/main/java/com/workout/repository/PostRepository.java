package com.workout.repository;

import com.workout.mapper.PostMapper;
import com.workout.model.post.Post;
import com.workout.model.post.PostUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PostRepository {

    private final PostMapper postMapper;

    public void save(Post post) {
        postMapper.save(post);
    }

    public List<Post> findAll() {
        return postMapper.findAll();
    }

    public List<Post> findAllByMemberId(Long memberId) {
        return postMapper.findAllByMemberId(memberId);
    }

    public Optional<Post> findById(Long id) {
        return postMapper.findById(id);
    }

    public void update(Long id, PostUpdateParam updateParam) {
        postMapper.update(id, updateParam);
    }

    public void delete(Long id) {
        postMapper.delete(id);
    }
}
