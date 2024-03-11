package team.retum.jobis.domain.notice.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.notice.model.Notice;
import team.retum.jobis.domain.notice.persistence.mapper.NoticeMapper;
import team.retum.jobis.domain.notice.persistence.repository.NoticeJpaRepository;
import team.retum.jobis.domain.notice.persistence.repository.vo.QQueryNoticeVO;
import team.retum.jobis.domain.notice.spi.NoticePort;
import team.retum.jobis.domain.notice.spi.vo.NoticeVO;

import java.util.List;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.groupBy;
import static team.retum.jobis.domain.notice.persistence.entity.QNoticeEntity.noticeEntity;


@RequiredArgsConstructor
@Repository
public class NoticePersistenceAdapter implements NoticePort {

    private final NoticeJpaRepository noticeJpaRepository;
    private final NoticeMapper noticeMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public void saveNotice(Notice notice) {
        noticeMapper.toDomain(
                noticeJpaRepository.save(
                        noticeMapper.toEntity(notice)
                )
        );
    }

    @Override
    public Optional<Notice> queryNoticeById(Long noticeId) {
        return noticeJpaRepository.findById(noticeId)
                .map(noticeMapper::toDomain);
    }

    @Override
    public void deleteNotice(Notice notice) {
        noticeJpaRepository.delete(noticeMapper.toEntity(notice));
    }

    @Override
    public List<NoticeVO> queryNotices() {
        return queryFactory
                .selectFrom(noticeEntity)
                .orderBy(noticeEntity.createdAt.desc())
                .transform(
                        groupBy(noticeEntity.id)
                                .list(
                                        new QQueryNoticeVO(
                                                noticeEntity.id,
                                                noticeEntity.title,
                                                noticeEntity.createdAt
                                        )
                                )
                )
                .stream()
                .map(NoticeVO.class::cast)
                .toList();
    }
}
