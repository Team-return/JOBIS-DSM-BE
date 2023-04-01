package team.returm.jobis.domain.auth.domain.repository;

import team.returm.jobis.domain.auth.domain.AuthCode;
import org.springframework.data.repository.CrudRepository;

public interface AuthCodeRepository extends CrudRepository<AuthCode, String> {

    boolean existsByEmailAndVerifiedIsTrue(String email);
}
