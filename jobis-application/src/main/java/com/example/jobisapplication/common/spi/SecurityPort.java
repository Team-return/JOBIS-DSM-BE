package com.example.jobisapplication.common.spi;

import com.example.jobisapplication.domain.auth.model.Authority;

import java.util.Optional;

public interface SecurityPort {
    Long getCurrentUserId();

    String encodePassword(String password);

    Authority getCurrentUserAuthority();

    boolean isPasswordMatch(String rawPassword, String encodedPassword);
}
