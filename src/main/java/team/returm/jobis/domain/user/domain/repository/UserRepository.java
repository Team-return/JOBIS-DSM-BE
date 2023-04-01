package team.returm.jobis.domain.user.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.returm.jobis.domain.user.domain.User;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final JPAQueryFactory queryFactory;

    public Optional<User> queryUserByAccountId(String accountId) {
        return userJpaRepository.findByAccountId(accountId);
    }

    public Optional<User> queryUserById(Long userId) {
        return userJpaRepository.findById(userId);
    }

    public boolean existsByAccountId(String accountId) {
        return userJpaRepository.existsByAccountId(accountId);
    }
}
