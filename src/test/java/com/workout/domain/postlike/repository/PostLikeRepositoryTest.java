package com.workout.domain.postlike.repository;

import com.workout.domain.member.model.Member;
import com.workout.domain.member.repository.MemberRepository;
import com.workout.domain.post.model.Post;
import com.workout.domain.post.repository.PostRepository;
import com.workout.domain.postlike.model.PostLike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class PostLikeRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostLikeRepository postLikeRepository;

    Member member;

    Post post;

    @BeforeEach
    void setUp(@Autowired MemberRepository memberRepository) {
        member = Member.builder()
                .email("user@gmail.com")
                .username("user")
                .password("testpass!")
                .profileImage("user.png")
                .build();

        memberRepository.save(member);

        post = Post.builder()
                .content("Hello World!")
                .memberId(member.getId())
                .build();

        postRepository.save(post);
    }

    @Test
    @DisplayName("PostLike 저장 테스트")
    void save() {
        // given
        PostLike postLike = PostLike.builder()
                .postId(post.getId())
                .memberId(member.getId())
                .build();

        // when
        postLikeRepository.save(postLike);

        // then
        List<PostLike> found = postLikeRepository.findByPostId(post.getId());
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getMemberId()).isEqualTo(member.getId());
    }

    @Test
    @DisplayName("PostLike postId별 조회 테스트")
    void findAllByPostId() {
        // given
        PostLike postLike = PostLike.builder()
                .postId(post.getId())
                .memberId(member.getId())
                .build();

        postLikeRepository.save(postLike);

        // when
        List<PostLike> found = postLikeRepository.findByPostId(post.getId());

        // then
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getMemberId()).isEqualTo(postLike.getMemberId());
    }

    @Test
    @DisplayName("PostLike memberId별 조회 테스트")
    void findAllByMemberId() {
        // given
        PostLike postLike = PostLike.builder()
                .postId(post.getId())
                .memberId(member.getId())
                .build();

        postLikeRepository.save(postLike);

        // when
        List<PostLike> found = postLikeRepository.findByMemberId(member.getId());

        // then
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getPostId()).isEqualTo(postLike.getPostId());
    }

    @Test
    @DisplayName("PostLike 저장 테스트")
    void delete() {
        // given
        PostLike postLike = PostLike.builder()
                .postId(post.getId())
                .memberId(member.getId())
                .build();

        postLikeRepository.save(postLike);

        // when
        postLikeRepository.delete(postLike.getId());

        // then
        List<PostLike> found = postLikeRepository.findByPostId(post.getId());
        assertThat(found).isEmpty();
    }
}