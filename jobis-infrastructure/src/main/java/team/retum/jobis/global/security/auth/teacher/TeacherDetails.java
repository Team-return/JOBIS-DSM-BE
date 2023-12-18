package team.retum.jobis.global.security.auth.teacher;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.teacher.persistence.entity.TeacherEntity;

import java.util.Collection;
import java.util.Collections;

@Getter
@RequiredArgsConstructor
public class TeacherDetails implements UserDetails {

    private transient final TeacherEntity teacher;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(Authority.TEACHER.name()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return teacher.getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
