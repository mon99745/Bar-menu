package com.example.bmm.converter;

import com.example.bmm.domain.user.User;
import com.example.bmm.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationConverter {
    private final UserRepository userRepository;

    /**
     * Authentication 에서 Username 반환
     * @param authentication
     * @return
     */
    public User getUserFromAuthentication(Authentication authentication) {
        String username = authentication.getName();

        return userRepository.findByUsername(username);
//        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
    }
}

