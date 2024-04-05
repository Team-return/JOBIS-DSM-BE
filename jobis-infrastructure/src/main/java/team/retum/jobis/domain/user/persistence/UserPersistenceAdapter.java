package team.retum.jobis.domain.user.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.persistence.mapper.UserMapper;
import team.retum.jobis.domain.user.persistence.repository.UserJpaRepository;
import team.retum.jobis.domain.user.spi.UserPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> queryUserByAccountId(String accountId) {
        return userJpaRepository.findByAccountId(accountId).map(userMapper::toDomain);
    }

    @Override
    public Optional<User> queryUserById(Long userId) {
        return userJpaRepository.findById(userId).map(userMapper::toDomain);
    }

    @Override
    public List<User> queryUsersByIds(List<Long> userIds) {
        return userJpaRepository.findAllByIdIn(userIds).stream().map(userMapper::toDomain).toList();
    }

    @Override
    public boolean existsUserByAccountId(String accountId) {
        return userJpaRepository.existsByAccountId(accountId);
    }

    @Override
    public User saveUser(User user) {
        return userMapper.toDomain(
            userJpaRepository.save(userMapper.toEntity(user))
        );
    }
}
