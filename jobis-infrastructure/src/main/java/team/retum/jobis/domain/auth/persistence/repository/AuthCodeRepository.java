package team.retum.jobis.domain.auth.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.auth.persistence.AuthCodeEntity;

public interface AuthCodeRepository extends CrudRepository<AuthCodeEntity, String> {

    boolean existsByEmailAndVerifiedIsTrue(String email);
}
