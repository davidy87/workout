package com.workout.repository;

import com.workout.mapper.QuestionMapper;
import com.workout.model.question.Question;
import com.workout.model.question.QuestionUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class QuestionRepository {

    private final QuestionMapper questionMapper;

    public void save(Question question) {
        questionMapper.save(question);
    }

    public List<Question> findAll() {
        return questionMapper.findAll();
    }

    public List<Question> findAllByMemberId(Long memberId) {
        return questionMapper.findAllByMemberId(memberId);
    }

    public Optional<Question> findById(Long id) {
        return questionMapper.findById(id);
    }

    public void update(Long id, QuestionUpdateParam updateParam) {
        questionMapper.update(id, updateParam);
    }

    public void delete(Long id) {
        questionMapper.delete(id);
    }
}
