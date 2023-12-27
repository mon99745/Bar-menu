package com.example.bmm.converter;

import com.example.bmc.exception.BmmError;
import com.example.bmc.exception.BmcException;
import com.example.bmm.domain.user.User;
import com.example.bmm.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
        Optional<User> userOptional = userRepository.findByUsername(authentication.getName());
        User user = userOptional.orElseThrow(()-> new BmcException(BmmError.BMM_ID_NOT_EXIST, null));
        return user;
    }
}

