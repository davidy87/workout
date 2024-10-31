package com.workout.repository;

import com.workout.domain.member.model.Member;
import com.workout.domain.member.repository.MemberRepository;
import com.workout.domain.question.model.Question;
import com.workout.domain.question.model.QuestionUpdateParam;
import com.workout.domain.question.repository.QuestionRepository;
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
class QuestionRepositoryTest {

    @Autowired
    QuestionRepository questionRepository;

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
    @DisplayName("Question 저장 테스트")
    void save() {
        // given
        Question question = Question.builder()
                .content("This is question.")
                .memberId(member.getId())
                .build();

        // when
        questionRepository.save(question);

        // then
        List<Question> found = questionRepository.findAll();
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getContent()).isEqualTo(question.getContent());
        assertThat(found.get(0).getMemberId()).isEqualTo(question.getMemberId());
    }

    @Test
    @DisplayName("Question 전체 조회 테스트")
    void findAll() {
        // given
        int size = 5;

        for (int i = 1; i <= size; i++) {
            Question question = Question.builder()
                    .content("This is question + " + i)
                    .memberId(member.getId())
                    .build();

            questionRepository.save(question);
        }

        // when
        List<Question> found = questionRepository.findAll();

        // then
        assertThat(found).hasSize(size);
    }

    @Test
    @DisplayName("memberId별 Question 전체 조회 테스트")
    void findAllByMemberId() {
        // given
        int size = 5;

        for (int i = 1; i <= size; i++) {
            Question question = Question.builder()
                    .content("This is question + " + i)
                    .memberId(member.getId())
                    .build();

            questionRepository.save(question);
        }

        // when
        List<Question> found = questionRepository.findAllByMemberId(member.getId());

        // then
        assertThat(found).hasSize(size);
    }

    @Test
    @DisplayName("Question id별 조회 테스트")
    void findById() {
        // given
        Question question = Question.builder()
                .content("This is question.")
                .memberId(member.getId())
                .build();

        questionRepository.save(question);

        // when
        Optional<Question> found = questionRepository.findById(question.getId());

        // then
        assertThat(found).isNotEmpty();
        assertThat(found.get().getContent()).isEqualTo(question.getContent());
        assertThat(found.get().getMemberId()).isEqualTo(question.getMemberId());
    }

    @Test
    @DisplayName("Question 수정 테스트")
    void update() {
        // given
        Question question = Question.builder()
                .content("This is question.")
                .memberId(member.getId())
                .build();

        questionRepository.save(question);

        // when
        QuestionUpdateParam updateParam = new QuestionUpdateParam("This is new question.");
        questionRepository.update(question.getId(), updateParam);

        // then
        Optional<Question> found = questionRepository.findById(question.getId());
        assertThat(found).isNotEmpty();
        assertThat(found.get().getContent()).isEqualTo(updateParam.getContent());
    }

    @Test
    @DisplayName("Question 삭제 테스트")
    void delete() {
        // given
        Question question = Question.builder()
                .content("This is question.")
                .memberId(member.getId())
                .build();

        questionRepository.save(question);

        // when
        questionRepository.delete(question.getId());

        // then
        Optional<Question> found = questionRepository.findById(question.getId());
        assertThat(found).isEmpty();
    }
}