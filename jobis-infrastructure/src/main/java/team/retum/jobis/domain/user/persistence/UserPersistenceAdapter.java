package team.retum.jobis.domain.user.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.user.exception.UserNotFoundException;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.persistence.mapper.UserMapper;
import team.retum.jobis.domain.user.persistence.repository.UserJpaRepository;
import team.retum.jobis.domain.user.spi.UserPort;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public User getByAccountIdOrThrow(String accountId) {
        return userJpaRepository.findByAccountId(accountId)
            .map(userMapper::toDomain)
            .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Override
    public User getByIdOrThrow(Long userId) {
        return userJpaRepository.findById(userId)
            .map(userMapper::toDomain)
            .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Override
    public List<User> getAllByIds(List<Long> userIds) {
        return userJpaRepository.findAllByIdIn(userIds).stream().map(userMapper::toDomain).toList();
    }

    @Override
    public boolean existsByAccountId(String accountId) {
        return userJpaRepository.existsByAccountId(accountId);
    }

    @Override
    public User save(User user) {
        return userMapper.toDomain(
            userJpaRepository.save(userMapper.toEntity(user))
        );
    }
}
