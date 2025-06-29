package team.retum.jobis.domain.notice.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.notice.exception.NoticeNotFoundException;
import team.retum.jobis.domain.notice.model.Notice;
import team.retum.jobis.domain.notice.model.ViewLog;
import team.retum.jobis.domain.notice.persistence.entity.NoticeEntity;
import team.retum.jobis.domain.notice.persistence.mapper.NoticeAttachmentMapper;
import team.retum.jobis.domain.notice.persistence.mapper.NoticeMapper;
import team.retum.jobis.domain.notice.persistence.mapper.ViewLogMapper;
import team.retum.jobis.domain.notice.persistence.repository.NoticeJpaRepository;
import team.retum.jobis.domain.notice.persistence.repository.ViewLogJpaRepository;
import team.retum.jobis.domain.notice.persistence.repository.vo.QQueryNoticeVO;
import team.retum.jobis.domain.notice.spi.NoticePort;
import team.retum.jobis.domain.notice.spi.vo.NoticeVO;
import team.retum.jobis.domain.notice.spi.vo.NoticeDetailVO;

import java.util.List;
import java.util.Optional;

import static team.retum.jobis.domain.notice.persistence.entity.QNoticeEntity.noticeEntity;
import static team.retum.jobis.domain.notice.persistence.entity.QViewLogEntity.viewLogEntity;

@RequiredArgsConstructor
@Repository
public class NoticePersistenceAdapter implements NoticePort {

    private final NoticeJpaRepository noticeJpaRepository;
    private final NoticeMapper noticeMapper;
    private final JPAQueryFactory queryFactory;
    private final ViewLogJpaRepository viewLogJpaRepository;
    private final ViewLogMapper viewLogMapper;
    private final NoticeAttachmentMapper noticeAttachmentMapper;

    @Override
    public Notice save(Notice notice) {
        return noticeMapper.toDomain(noticeJpaRepository.save(noticeMapper.toEntity(notice)));
    }

    @Override
    public Optional<NoticeDetailVO> getById(Long noticeId) {

        NoticeEntity entity = noticeJpaRepository.findById(noticeId)
                .orElseThrow(() -> NoticeNotFoundException.EXCEPTION);

        Long viewCount = queryFactory
                .select(viewLogEntity.id.count())
                .from(viewLogEntity)
                .where(viewLogEntity.notice.id.eq(noticeId))
                .fetchOne();

        NoticeDetailVO vo = new NoticeDetailVO(
                entity.getId(),
                entity.getTitle(),
                entity.getCreatedAt(),
                viewCount,
                entity.getContent()
        );
        vo.setAttachments(noticeAttachmentMapper.toDomainList(entity.getAttachments()));

        return Optional.of(vo);
    }

    @Override
    public void delete(Notice notice) {
        noticeJpaRepository.delete(noticeMapper.toEntity(notice));
    }

    @Override
    public void saveViewLog(ViewLog viewLog) {
        viewLogJpaRepository.save(viewLogMapper.toEntity(viewLog));
    }

    @Override
    public List<NoticeVO> getNotices() {
        return queryFactory
            .select(
                new QQueryNoticeVO(
                    noticeEntity.id,
                    noticeEntity.title,
                    noticeEntity.createdAt
                )
            )
            .from(noticeEntity)
            .orderBy(noticeEntity.createdAt.desc())
            .fetch()
            .stream()
            .map(NoticeVO.class::cast)
            .toList();
    }

    @Override
    public Boolean existsViewLogByNoticeIdAndStudentId(Long noticeId, Long studentId) {
        Integer result = queryFactory
                .selectOne()
                .from(viewLogEntity)
                .where(
                        viewLogEntity.notice.id.eq(noticeId),
                        viewLogEntity.student.id.eq(studentId)
                )
                .fetchFirst();

        return result != null;
    }
}
