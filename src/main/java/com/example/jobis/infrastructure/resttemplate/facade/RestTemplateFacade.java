package com.example.jobis.infrastructure.resttemplate.facade;

import com.example.jobis.infrastructure.resttemplate.dto.response.BusinessNumberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Component
public class RestTemplateFacade {

    @Value("${rest-template.access-key}")
    private String accessKey;

    private final RestTemplate restTemplate = new RestTemplate();


    public BusinessNumberResponse getApi(String businessNumber) {
        final HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();
        URI uri = UriComponentsBuilder
                .fromUriString("https://bizno.net/api/fapi")
                .queryParam("key", accessKey)
                .queryParam("gb", 1)
                .queryParam("q", businessNumber)
                .build()
                .toUri();

        final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(uri, HttpMethod.POST, entity, BusinessNumberResponse.class).getBody();
    }
}
