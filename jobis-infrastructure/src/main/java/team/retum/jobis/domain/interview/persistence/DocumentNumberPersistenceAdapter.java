package team.retum.jobis.domain.interview.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.interview.model.DocumentNumber;
import team.retum.jobis.domain.interview.persistence.mapper.DocumentNumberMapper;
import team.retum.jobis.domain.interview.persistence.repository.DocumentNumberJpaRepository;
import team.retum.jobis.domain.interview.persistence.repository.vo.QQueryInterviewVO;
import team.retum.jobis.domain.interview.spi.DocumentNumberPort;
import team.retum.jobis.domain.interview.spi.vo.DocumentNumberDetailVO;
import team.retum.jobis.domain.interview.spi.vo.InterviewVO;

import java.util.List;
import java.util.Optional;

import static team.retum.jobis.domain.interview.persistence.entity.QInterviewEntity.interviewEntity;

@Repository
@RequiredArgsConstructor
public class DocumentNumberPersistenceAdapter implements DocumentNumberPort {

    private final DocumentNumberJpaRepository documentNumberJpaRepository;
    private final DocumentNumberMapper documentNumberMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public DocumentNumber save(DocumentNumber documentNumber) {
        return documentNumberMapper.toDomain(
            documentNumberJpaRepository.save(
                documentNumberMapper.toEntity(documentNumber)
            )
        );
    }

    @Override
    public void delete(DocumentNumber documentNumber) {
        documentNumberJpaRepository.deleteById(documentNumber.getId());
    }

    @Override
    public Optional<DocumentNumber> getById(Long documentNumberId) {
        return documentNumberJpaRepository.findById(documentNumberId)
            .map(documentNumberMapper::toDomain);
    }

    @Override
    public List<DocumentNumber> getAll() {
        return documentNumberJpaRepository.findAll().stream()
            .map(documentNumberMapper::toDomain)
            .toList();
    }

    @Override
    public Optional<DocumentNumberDetailVO> getDetailById(Long documentNumberId) {
        return documentNumberJpaRepository.findById(documentNumberId)
            .map(entity -> {
                List<InterviewVO> interviews = queryFactory
                    .select(
                        new QQueryInterviewVO(
                            interviewEntity.id,
                            interviewEntity.interviewType,
                            interviewEntity.startDate,
                            interviewEntity.endDate,
                            interviewEntity.interviewTime,
                            interviewEntity.companyName,
                            interviewEntity.location
                        )
                    )
                    .from(interviewEntity)
                    .where(interviewEntity.documentNumber.id.eq(documentNumberId))
                    .orderBy(interviewEntity.startDate.asc())
                    .fetch().stream()
                    .map(InterviewVO.class::cast)
                    .toList();

                return new DocumentNumberDetailVO(
                    entity.getId(),
                    entity.getDocumentNumber(),
                    interviews
                );
            });
    }
}
