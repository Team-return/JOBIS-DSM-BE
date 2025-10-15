package team.retum.jobis.domain.notice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.notice.persistence.entity.NoticeEntity;

public interface NoticeJpaRepository extends JpaRepository<NoticeEntity, Long> {

}
