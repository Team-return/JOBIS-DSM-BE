package com.example.jobis.global.util.jms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "spring.mail")
public class JmsProperties {

    private final String username;
    private final Long authExp;
    private final String suffix;
}
