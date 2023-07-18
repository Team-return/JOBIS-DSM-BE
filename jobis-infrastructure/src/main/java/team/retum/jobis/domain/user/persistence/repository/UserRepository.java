package team.retum.jobis.domain.user.persistence.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.user.persistence.UserEntity;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final JPAQueryFactory queryFactory;

    public Optional<UserEntity> queryUserByAccountId(String accountId) {
        return userJpaRepository.findByAccountId(accountId);
    }

    public Optional<UserEntity> queryUserById(Long userId) {
        return userJpaRepository.findById(userId);
    }

    public boolean existsByAccountId(String accountId) {
        return userJpaRepository.existsByAccountId(accountId);
    }
}
