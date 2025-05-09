package team.retum.jobis.domain.application.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import team.retum.jobis.domain.application.persistence.entity.ApplicationEntity;

import java.util.List;
import java.util.Optional;

public interface ApplicationJpaRepository extends JpaRepository<ApplicationEntity, Long> {

    List<ApplicationEntity> findByIdIn(List<Long> applicationIds);

    void deleteByIdIn(List<Long> applicationIds);

    @Modifying
    @Query("delete from ApplicationAttachmentEntity a where a.application.id = ?1")
    void deleteAttachmentsByApplicationId(Long applicationId);

    @Query("select a from ApplicationEntity a join fetch a.applicationAttachments where a.id=?1")
    Optional<ApplicationEntity> findByIdFetch(Long applicationId);
}
