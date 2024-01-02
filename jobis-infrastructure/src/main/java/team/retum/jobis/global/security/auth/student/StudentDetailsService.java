package team.retum.jobis.global.security.auth.student;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;
import team.retum.jobis.global.exception.InvalidTokenException;
import team.retum.jobis.global.security.auth.CurrentUserHolder;

import static team.retum.jobis.global.config.cache.CacheName.STUDENT_USER;

@CacheConfig(cacheNames = STUDENT_USER)
@Component
@RequiredArgsConstructor
public class StudentDetailsService implements UserDetailsService {

    private final StudentJpaRepository studentJpaRepository;

    @Cacheable
    @Override
    public UserDetails loadUserByUsername(String studentId) throws UsernameNotFoundException {
        StudentEntity studentEntity = studentJpaRepository.findById(
                Long.valueOf(studentId)
        ).orElseThrow(() -> InvalidTokenException.EXCEPTION);
        CurrentUserHolder.setUser(studentEntity);

        return new StudentDetails(studentEntity.getId());
    }
}
