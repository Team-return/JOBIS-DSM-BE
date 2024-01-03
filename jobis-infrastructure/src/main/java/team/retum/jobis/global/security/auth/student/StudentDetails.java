package team.retum.jobis.global.security.auth.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;

import java.util.Collection;
import java.util.Collections;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class StudentDetails implements UserDetails {

    private final StudentEntity student;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(Authority.STUDENT.name()));
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return student.getId().toString();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
