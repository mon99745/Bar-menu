package com.example.bmm.config.auth;

import com.example.bmc.exception.BmmError;
import com.example.bmc.exception.BmcException;
import com.example.bmm.domain.user.User;
import com.example.bmm.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
/*
 UserDetailsService를 상속받게 되면 오버라이딩을 해야하는데 이 메소드는 DB에 username이 있는지 확인하는 메소드입니다.
 PrincipalDetail(principal)을 리턴을 하게 되면 시큐리티의 세션에 유저 정보가 저장됩니다.
*/
public class PrincipalDetailService implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User principal = userOptional.orElseThrow(()-> new BmcException(BmmError.BMM_ID_NOT_EXIST, null));
        return new PrincipalDetail(principal);
    }
}