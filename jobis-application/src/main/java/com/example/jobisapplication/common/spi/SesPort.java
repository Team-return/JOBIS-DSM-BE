package com.example.jobisapplication.common.spi;

public interface SesPort {

    void sendMail(String authCode, String sendTo);
}
