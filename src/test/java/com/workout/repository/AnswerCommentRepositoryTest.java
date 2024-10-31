package com.workout.repository;

import com.workout.domain.answer.model.Answer;
import com.workout.domain.answer.repository.AnswerRepository;
import com.workout.domain.answercomment.repository.AnswerCommentRepository;
import com.workout.domain.answercomment.model.AnswerComment;
import com.workout.domain.answercomment.model.AnswerCommentUpdateParam;
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
class AnswerCommentRepositoryTest {

    @Autowired
    AnswerCommentRepository answerCommentRepository;

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
    @DisplayName("AnswerComment 저장 테스트")
    void save() {
        // given
        AnswerComment answerComment = AnswerComment.builder()
                .content("This is answer comment.")
                .answerId(answer.getId())
                .memberId(questioner.getId())
                .build();

        // when
        answerCommentRepository.save(answerComment);

        // then
        List<AnswerComment> found = answerCommentRepository.findAllByAnswerId(answer.getId());
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getContent()).isEqualTo(answerComment.getContent());
        assertThat(found.get(0).getMemberId()).isEqualTo(questioner.getId());
    }

    @Test
    @DisplayName("answerId별 AnswerComment 전체 조회 테스트")
    void findAllByAnswerId() {
        // given
        int size = 5;

        for (int i = 1; i <= size; i++) {
            AnswerComment answerComment = AnswerComment.builder()
                    .content("This is answer comment" + i)
                    .answerId(answer.getId())
                    .memberId(questioner.getId())
                    .build();

            answerCommentRepository.save(answerComment);
        }

        // when
        List<AnswerComment> found = answerCommentRepository.findAllByAnswerId(answer.getId());

        // then
        assertThat(found).hasSize(size);
    }

    @Test
    @DisplayName("memberId별 AnswerComment 전체 조회 테스트")
    void findAllByMemberId() {
        // given
        int size = 5;

        for (int i = 1; i <= size; i++) {
            AnswerComment answerComment = AnswerComment.builder()
                    .content("This is answer comment" + i)
                    .answerId(answer.getId())
                    .memberId(questioner.getId())
                    .build();

            answerCommentRepository.save(answerComment);
        }

        // when
        List<AnswerComment> found = answerCommentRepository.findAllByMemberId(questioner.getId());

        // then
        assertThat(found).hasSize(size);
    }

    @Test
    @DisplayName("AnswerComment 수정 테스트")
    void update() {
        // given
        AnswerComment answerComment = AnswerComment.builder()
                .content("This is answer comment.")
                .answerId(answer.getId())
                .memberId(questioner.getId())
                .build();

        answerCommentRepository.save(answerComment);

        // when
        AnswerCommentUpdateParam updateParam = new AnswerCommentUpdateParam("This is new answer comment.");
        answerCommentRepository.update(answerComment.getId(), updateParam);

        // then
        List<AnswerComment> found = answerCommentRepository.findAllByAnswerId(answer.getId());
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getContent()).isEqualTo(updateParam.getContent());
    }

    @Test
    @DisplayName("AnswerComment 삭제 테스트")
    void delete() {
        // given
        AnswerComment answerComment = AnswerComment.builder()
                .content("This is answer comment.")
                .answerId(answer.getId())
                .memberId(questioner.getId())
                .build();

        answerCommentRepository.save(answerComment);

        // when
        answerCommentRepository.delete(answerComment.getId());

        // then
        List<AnswerComment> found = answerCommentRepository.findAllByAnswerId(answer.getId());
        assertThat(found).isEmpty();
    }
}