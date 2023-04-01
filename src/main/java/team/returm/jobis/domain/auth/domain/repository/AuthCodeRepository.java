package team.returm.jobis.domain.auth.domain.repository;

import org.springframework.data.repository.CrudRepository;
import team.returm.jobis.domain.auth.domain.AuthCode;

public interface AuthCodeRepository extends CrudRepository<AuthCode, String> {

    boolean existsByEmailAndVerifiedIsTrue(String email);
}
