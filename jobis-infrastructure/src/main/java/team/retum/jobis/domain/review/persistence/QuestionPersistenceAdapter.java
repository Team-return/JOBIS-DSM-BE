package team.retum.jobis.domain.review.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.review.model.Question;
import team.retum.jobis.domain.review.persistence.mapper.QuestionMapper;
import team.retum.jobis.domain.review.persistence.repository.QuestionJpaRepository;
import team.retum.jobis.domain.review.spi.CommandQuestionPort;
import team.retum.jobis.domain.review.spi.QueryQuestionPort;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class QuestionPersistenceAdapter implements QueryQuestionPort, CommandQuestionPort {

    private final QuestionJpaRepository questionJpaRepository;
    private final QuestionMapper questionMapper;

    @Override
    public List<Question> queryAllQuestions() {
        return questionJpaRepository.findAll().stream()
            .map(questionMapper::toDomain)
            .toList();
    }

    @Override
    public Question save(Question question) {
        return questionMapper.toDomain(
            questionJpaRepository.save(questionMapper.toEntity(question))
        );
    }
}
