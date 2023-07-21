package com.example.jobisapplication.common.spi;

public interface SecurityPort {
    Long getCurrentUserId();

    String encodePassword(String password);
}
