package team.retum.jobis.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.persistence.mapper.CompanyMapper;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.persistence.mapper.StudentMapper;
import team.retum.jobis.domain.teacher.model.Teacher;
import team.retum.jobis.domain.teacher.persistence.mapper.TeacherMapper;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.persistence.mapper.UserMapper;
import team.retum.jobis.domain.user.persistence.repository.UserJpaRepository;
import team.retum.jobis.global.exception.InvalidTokenException;
import team.retum.jobis.global.security.auth.company.CompanyDetails;
import team.retum.jobis.global.security.auth.student.StudentDetails;
import team.retum.jobis.global.security.auth.teacher.TeacherDetails;

@RequiredArgsConstructor
@Component
public class SecurityAdapter implements SecurityPort {

    private final PasswordEncoder passwordEncoder;
    private final CompanyMapper companyMapper;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final UserMapper userMapper;
    private final UserJpaRepository userJpaRepository;

    @Override
    public Long getCurrentUserId() {
        return Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public Authority getCurrentUserAuthority() {
        UserDetails currentUser = (UserDetails) getCurrentUserDetails();
        return Authority.valueOf(currentUser.getAuthorities().iterator().next().getAuthority());
    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public Company getCurrentCompany() {
        CompanyDetails companyDetails = (CompanyDetails) getCurrentUserDetails();
        return companyMapper.toDomain(companyDetails.getCompany());
    }

    @Override
    public Student getCurrentStudent() {
        StudentDetails studentDetails = (StudentDetails) getCurrentUserDetails();
        return studentMapper.toDomain(studentDetails.getStudent());
    }

    @Override
    public Teacher getCurrentTeacher() {
        TeacherDetails teacherDetails = (TeacherDetails) getCurrentUserDetails();
        return teacherMapper.toDomain(teacherDetails.getTeacher());
    }

    @Override
    public User getCurrentUser() {
        Long currentUserId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        return userJpaRepository.findById(currentUserId)
                .map(userMapper::toDomain)
                .orElseThrow(() -> InvalidTokenException.EXCEPTION);
    }

    private Object getCurrentUserDetails() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}