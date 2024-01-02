package team.retum.jobis.global.security.auth.teacher;

<<<<<<< HEAD
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonIgnore;
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
>>>>>>> 6bf52aa6652f4b8c7c434c98e288ae8bc81225b4
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.teacher.persistence.entity.TeacherEntity;

import java.util.Collection;
import java.util.Collections;

@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class TeacherDetails implements UserDetails {

<<<<<<< HEAD
    private final transient TeacherEntity teacher;

=======
>>>>>>> 6bf52aa6652f4b8c7c434c98e288ae8bc81225b4
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(Authority.TEACHER.name()));
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return teacher.getId().toString();
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
