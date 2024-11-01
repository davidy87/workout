package com.workout.domain.question.repository;

import com.workout.mapper.QuestionMapper;
import com.workout.domain.question.model.Question;
import com.workout.domain.question.model.QuestionUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    private final QuestionMapper questionMapper;

    @Override
    public void save(Question question) {
        questionMapper.save(question);
    }

    @Override
    public List<Question> findAll() {
        return questionMapper.findAll();
    }

    @Override
    public List<Question> findAllByMemberId(Long memberId) {
        return questionMapper.findAllByMemberId(memberId);
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questionMapper.findById(id);
    }

    @Override
    public void update(Long id, QuestionUpdateParam updateParam) {
        questionMapper.update(id, updateParam);
    }

    @Override
    public void delete(Long id) {
        questionMapper.delete(id);
    }
}
