package team.retum.jobis.domain.auth.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.auth.persistence.AuthCode;

public interface AuthCodeRepository extends CrudRepository<AuthCode, String> {

    boolean existsByEmailAndVerifiedIsTrue(String email);
}
