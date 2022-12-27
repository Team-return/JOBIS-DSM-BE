package com.example.jobis.domain.user.controller;

import com.example.jobis.domain.auth.domain.RefreshToken;
import com.example.jobis.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.jobis.domain.user.controller.dto.request.LoginRequest;
import com.example.jobis.domain.user.controller.dto.response.TokenResponse;
import com.example.jobis.domain.user.service.LoginService;
import com.example.jobis.domain.user.service.TokenReissueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final LoginService loginService;
    private final TokenReissueService tokenReissueService;

    private final RefreshTokenRepository refreshTokenRepository;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.execute(request);
    }

    @PutMapping("/reissue")
    public TokenResponse reissue(@RequestHeader("X-Refresh-Token") String token) {
        return tokenReissueService.execute(token);
    }

    @GetMapping("/a")
    public List<RefreshToken> ff() {
        return (List<RefreshToken>) refreshTokenRepository.findAll();
    }
}
