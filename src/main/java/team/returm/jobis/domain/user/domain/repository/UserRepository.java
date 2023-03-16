package team.returm.jobis.domain.user.domain.repository;

import team.returm.jobis.domain.user.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final JPAQueryFactory queryFactory;

    public Optional<User> queryUserByAccountId(String accountId) {
        return userJpaRepository.findByAccountId(accountId);
    }

    public User saveUser(User user) {
        return userJpaRepository.save(user);
    }

    public Optional<User> queryUserById(UUID userId) {
        return userJpaRepository.findById(userId);
    }
}
