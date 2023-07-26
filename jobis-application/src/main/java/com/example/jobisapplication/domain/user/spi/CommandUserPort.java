package com.example.jobisapplication.domain.user.spi;

import com.example.jobisapplication.domain.user.model.User;

public interface CommandUserPort {
    User saveUser(User user);
}
