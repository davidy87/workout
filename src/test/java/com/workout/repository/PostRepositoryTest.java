package com.workout.repository;

import com.workout.model.member.Member;
import com.workout.model.post.Post;
import com.workout.model.post.PostUpdateParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    Member member;

    @BeforeEach
    void setUp(@Autowired MemberRepository memberRepository) {
        member = Member.builder()
                .email("user@gmail.com")
                .username("user")
                .password("testpass!")
                .profileImage("user.png")
                .build();

        memberRepository.save(member);
    }

    @Test
    @DisplayName("Post 저장 테스트")
    void save() {
        // given
        Post post = Post.builder()
                .content("Hello World!")
                .memberId(member.getId())
                .build();

        // when
        postRepository.save(post);

        // then
        List<Post> found = postRepository.findAll();
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getContent()).isEqualTo(post.getContent());
    }

    @Test
    @DisplayName("Post 전체 조회 테스트")
    void findAll() {
        // given
        int size = 5;

        for (int i = 1; i <= size; i++) {
            Post post = Post.builder()
                    .content("content " + i)
                    .memberId(member.getId())
                    .build();

            postRepository.save(post);
        }

        // when
        List<Post> found = postRepository.findAll();

        // then
        assertThat(found).hasSize(size);
    }

    @Test
    @DisplayName("Post memberId별 전체 조회 테스트")
    void findAllByMemberId() {
        // given
        int size = 5;

        for (int i = 1; i <= size; i++) {
            Post post = Post.builder()
                    .content("content " + i)
                    .memberId(member.getId())
                    .build();

            postRepository.save(post);
        }

        // when
        List<Post> found = postRepository.findAllByMemberId(member.getId());

        // then
        assertThat(found).hasSize(size);
    }

    @Test
    @DisplayName("Post id별 조회 테스트")
    void findById() {
        // given
        Post post = Post.builder()
                .content("Hello World!")
                .memberId(member.getId())
                .build();

        postRepository.save(post);

        // when
        Optional<Post> found = postRepository.findById(post.getId());

        // then
        assertThat(found).isNotEmpty();
        assertThat(found.get().getContent()).isEqualTo(post.getContent());
    }

    @Test
    @DisplayName("Post 수정 테스트")
    void update() {
        // given
        Post post = Post.builder()
                .content("Hello World!")
                .memberId(member.getId())
                .build();

        postRepository.save(post);

        // when
        PostUpdateParam updateParam = new PostUpdateParam("Bye World!");
        postRepository.update(post.getId(), updateParam);

        // then
        Optional<Post> found = postRepository.findById(post.getId());
        assertThat(found).isNotEmpty();
        assertThat(found.get().getContent()).isEqualTo(updateParam.getContent());
    }

    @Test
    @DisplayName("Post 삭제 테스트")
    void delete() {
        // given
        Post post = Post.builder()
                .content("Hello World!")
                .memberId(member.getId())
                .build();

        postRepository.save(post);

        // when
        postRepository.delete(post.getId());

        // then
        Optional<Post> found = postRepository.findById(post.getId());
        assertThat(found).isEmpty();
    }
}