package com.workout.domain.answerlike.repository;

import com.workout.domain.answer.model.Answer;
import com.workout.domain.answer.repository.AnswerRepository;
import com.workout.domain.answerlike.model.AnswerLike;
import com.workout.domain.member.model.Member;
import com.workout.domain.member.repository.MemberRepository;
import com.workout.domain.question.model.Question;
import com.workout.domain.question.repository.QuestionRepository;
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
class AnswerLikeRepositoryTest {

    @Autowired
    AnswerLikeRepository answerLikeRepository;

    Member questioner;

    Member answerer;

    Answer answer;

    @BeforeEach
    void setUp(@Autowired MemberRepository memberRepository,
               @Autowired QuestionRepository questionRepository,
               @Autowired AnswerRepository answerRepository) {

        questioner = Member.builder()
                .email("questioner@gmail.com")
                .username("questioner")
                .password("testpass!")
                .profileImage("questioner.png")
                .build();

        memberRepository.save(questioner);

        answerer = Member.builder()
                .email("answerer@gmail.com")
                .username("answerer")
                .password("testpass!")
                .profileImage("answerer.png")
                .build();

        memberRepository.save(answerer);

        Question question = Question.builder()
                .content("This is question.")
                .memberId(questioner.getId())
                .build();

        questionRepository.save(question);

        answer = Answer.builder()
                .content("This is answer.")
                .questionId(question.getId())
                .memberId(answerer.getId())
                .build();

        answerRepository.save(answer);
    }

    @Test
    @DisplayName("AnswerLike 저장 테스트")
    void save() {
        // given
        AnswerLike answerLike = AnswerLike.builder()
                .answerId(answer.getId())
                .memberId(questioner.getId())
                .build();

        // when
        answerLikeRepository.save(answerLike);

        // then
        List<AnswerLike> found = answerLikeRepository.findAllByAnswerId(answer.getId());
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getMemberId()).isEqualTo(answerLike.getMemberId());
    }

    @Test
    @DisplayName("answerId별 AnswerLike 전체 조회")
    void findAllByAnswerId() {
        // given
        answerLikeRepository.save(AnswerLike.builder()
                .answerId(answer.getId())
                .memberId(questioner.getId())
                .build());

        answerLikeRepository.save(AnswerLike.builder()
                .answerId(answer.getId())
                .memberId(answerer.getId())
                .build());

        // when
        List<AnswerLike> found = answerLikeRepository.findAllByAnswerId(answer.getId());

        // then
        assertThat(found).hasSize(2);
    }

    @Test
    @DisplayName("AnswerLike 삭제 테스트")
    void delete() {
        // given
        AnswerLike answerLike = AnswerLike.builder()
                .answerId(answer.getId())
                .memberId(questioner.getId())
                .build();

        answerLikeRepository.save(answerLike);

        // when
        answerLikeRepository.delete(answerLike.getId());

        // then
        List<AnswerLike> found = answerLikeRepository.findAllByAnswerId(answer.getId());
        assertThat(found).isEmpty();
    }
}