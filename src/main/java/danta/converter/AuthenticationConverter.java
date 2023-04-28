package danta.converter;

import danta.domain.user.User;
import danta.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationConverter {
    private final UserRepository userRepository;

    public User getUserFromAuthentication(Authentication authentication) {
        String username = authentication.getName();

        return userRepository.findByUsername(username);
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
    }
}

