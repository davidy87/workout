package com.workout.repository;

import com.workout.model.member.Member;
import com.workout.model.post.Post;
import com.workout.model.postcomment.PostComment;
import com.workout.model.postcomment.PostCommentUpdateParam;
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
class PostCommentRepositoryTest {

    @Autowired
    PostCommentRepository postCommentRepository;

    Member member;

    Post post;

    @BeforeEach
    void setUp(@Autowired MemberRepository memberRepository, @Autowired PostRepository postRepository) {
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
    @DisplayName("PostComment 저장 테스트")
    void save() {
        // given
        PostComment postComment = PostComment.builder()
                .content("This is comment.")
                .postId(post.getId())
                .memberId(member.getId())
                .build();

        // when
        postCommentRepository.save(postComment);

        // then
        Optional<PostComment> found = postCommentRepository.findById(postComment.getId());
        assertThat(found).isNotEmpty();
        assertThat(found.get().getContent()).isEqualTo(postComment.getContent());
        assertThat(found.get().getPostId()).isEqualTo(post.getId());
        assertThat(found.get().getMemberId()).isEqualTo(member.getId());
    }

    @Test
    @DisplayName("PostComment 전체 조회 테스트")
    void findAll() {
        // given
        int size = 5;

        for (int i = 1; i <= size; i++) {
            PostComment postComment = PostComment.builder()
                    .content("This is comment " + i + ".")
                    .postId(post.getId())
                    .memberId(member.getId())
                    .build();

            postCommentRepository.save(postComment);
        }

        // when
        List<PostComment> found = postCommentRepository.findAll();

        // then
        assertThat(found).hasSize(size);
    }

    @Test
    @DisplayName("PostComment id별 조회 테스트")
    void findById() {
        // given
        PostComment postComment = PostComment.builder()
                .content("This is comment.")
                .postId(post.getId())
                .memberId(member.getId())
                .build();

        postCommentRepository.save(postComment);

        // when
        Optional<PostComment> found = postCommentRepository.findById(postComment.getId());

        // then
        assertThat(found).isNotEmpty();
        assertThat(found.get().getContent()).isEqualTo(postComment.getContent());
        assertThat(found.get().getPostId()).isEqualTo(post.getId());
        assertThat(found.get().getMemberId()).isEqualTo(member.getId());
    }

    @Test
    @DisplayName("PostComment 수정 테스트")
    void update() {
        // given
        PostComment postComment = PostComment.builder()
                .content("This is comment.")
                .postId(post.getId())
                .memberId(member.getId())
                .build();

        postCommentRepository.save(postComment);

        // when
        PostCommentUpdateParam updateParam = new PostCommentUpdateParam("This is updated comment.");
        postCommentRepository.update(postComment.getId(), updateParam);

        // then
        Optional<PostComment> found = postCommentRepository.findById(postComment.getId());
        assertThat(found).isNotEmpty();
        assertThat(found.get().getContent()).isEqualTo(updateParam.getContent());
    }

    @Test
    @DisplayName("PostComment 삭제 테스트")
    void delete() {
        // given
        PostComment postComment = PostComment.builder()
                .content("This is comment.")
                .postId(post.getId())
                .memberId(member.getId())
                .build();

        postCommentRepository.save(postComment);

        // when
        postCommentRepository.delete(postComment.getId());

        // then
        Optional<PostComment> found = postCommentRepository.findById(postComment.getId());
        assertThat(found).isEmpty();
    }
}