package com.workout.repository;

import com.workout.model.answer.Answer;
import com.workout.model.answer.AnswerUpdateParam;
import com.workout.model.member.Member;
import com.workout.model.question.Question;
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
class AnswerRepositoryTest {

    @Autowired
    AnswerRepository answerRepository;

    Question question;

    Member questioner;

    Member answerer;

    @BeforeEach
    void setUp(@Autowired MemberRepository memberRepository, @Autowired QuestionRepository questionRepository) {
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

        question = Question.builder()
                .content("This is question.")
                .memberId(questioner.getId())
                .build();

        questionRepository.save(question);
    }

    @Test
    @DisplayName("Answer 저장 테스트")
    void save() {
        // given
        Answer answer = Answer.builder()
                .content("This is answer.")
                .questionId(question.getId())
                .memberId(answerer.getId())
                .build();

        // when
        answerRepository.save(answer);

        // then
        List<Answer> found = answerRepository.findAllByMemberId(answerer.getId());
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getContent()).isEqualTo(answer.getContent());
        assertThat(found.get(0).getQuestionId()).isEqualTo(answer.getQuestionId());
        assertThat(found.get(0).getMemberId()).isEqualTo(answerer.getId());
    }

    @Test
    @DisplayName("questionId별 Answer 전체 조회 테스트")
    void findAllByQuestionId() {
        // given
        int size = 5;

        for (int i = 1; i <= size; i++) {
            Answer answer = Answer.builder()
                    .content("This is answer " + i)
                    .questionId(question.getId())
                    .memberId(answerer.getId())
                    .build();

            answerRepository.save(answer);
        }

        // when
        List<Answer> found = answerRepository.findAllByQuestionId(question.getId());

        // then
        assertThat(found).hasSize(size);
    }

    @Test
    @DisplayName("memberId별 Answer 전체 조회 테스트")
    void findAllByMemberId() {
        // given
        int size = 5;

        for (int i = 1; i <= size; i++) {
            Answer answer = Answer.builder()
                    .content("This is answer " + i)
                    .questionId(question.getId())
                    .memberId(answerer.getId())
                    .build();

            answerRepository.save(answer);
        }

        // when
        List<Answer> found = answerRepository.findAllByMemberId(answerer.getId());

        // then
        assertThat(found).hasSize(size);
    }

    @Test
    @DisplayName("Answer 수정 테스트")
    void update() {
        // given
        Answer answer = Answer.builder()
                .content("This is answer.")
                .questionId(question.getId())
                .memberId(answerer.getId())
                .build();

        answerRepository.save(answer);

        // when
        AnswerUpdateParam updateParam = new AnswerUpdateParam("This is new content.");
        answerRepository.update(answer.getId(), updateParam);

        // then
        List<Answer> found = answerRepository.findAllByQuestionId(question.getId());
        assertThat(found.get(0).getContent()).isEqualTo(updateParam.getContent());
    }

    @Test
    @DisplayName("Answer 삭제 테스트")
    void delete() {
        // given
        Answer answer = Answer.builder()
                .content("This is answer.")
                .questionId(question.getId())
                .memberId(answerer.getId())
                .build();

        answerRepository.save(answer);

        // when
        answerRepository.delete(answer.getId());

        // then
        List<Answer> found = answerRepository.findAllByQuestionId(question.getId());
        assertThat(found).isEmpty();
    }
}