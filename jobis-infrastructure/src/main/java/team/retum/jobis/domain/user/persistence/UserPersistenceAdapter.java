package team.retum.jobis.domain.user.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.persistence.entity.TopicSubscriptionEntity;
import team.retum.jobis.domain.notification.persistence.repository.TopicSubscriptionJpaRepository;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;
import team.retum.jobis.domain.user.exception.UserNotFoundException;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.persistence.mapper.UserMapper;
import team.retum.jobis.domain.user.persistence.repository.UserJpaRepository;
import team.retum.jobis.domain.user.spi.UserPort;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;
    private final StudentJpaRepository studentJpaRepository;
    private final TopicSubscriptionJpaRepository topicSubscriptionJpaRepository;

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

    @Override
    public User getByStudentId(Long studentId) {
        return studentJpaRepository.findWithUserEntityById(studentId)
            .map(StudentEntity::getUserEntity)
            .map(userMapper::toDomain)
            .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Override
    public List<String> getDeviceTokenByTopic(Topic topic) {
        return topicSubscriptionJpaRepository.findAllByTopicAndIsSubscribedTrue(topic).stream()
            .map(TopicSubscriptionEntity::getDeviceToken)
            .collect(Collectors.toList());
    }

    @Override
    public User getUserIdByDeviceToken(String deviceToken) {
        return userJpaRepository.findByToken(deviceToken)
            .map(userMapper::toDomain)
            .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Override
    public List<User> getAllByDeviceTokenExists() {
        return userJpaRepository.findByTokenIsNotNull();
    }
}
