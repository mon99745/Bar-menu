package danta.config.auth;

import danta.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//  스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
//  스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다.

// UserDatails 객체 상속시 시큐리티의 고유한 세션저장소에 저장을 할 수 있게 된다.
//  스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
//  스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다.

@RequiredArgsConstructor
@Getter
// UserDatails 객체 상속시 시큐리티의 고유한 세션저장소에 저장을 할 수 있게 된다.
public class PrincipalDetail implements UserDetails {

    private User user; // 콤포지션 : 객체를 품고 있는 것


    public void setUser(User user) {
        this.user = user;
    }

    //생성자
    public PrincipalDetail(User user) {
        this.user = user;
    }

    //사용자 패스워드
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //사용자 아이디
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //사용자 이메일

    //사용자 이메일
    public String getEmail() {
        return user.getEmail();
    }

    //사용자 닉네임
    public String getNickname() {
        return user.getNickname();
    }

    //사용자 pk
    public Long getAuthId() {
        return user.getAuthId();
    }

    //계정이 만료되었는지 (true: 만료되지 않음)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있는지 (true: 잠겨있지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //패스워드가 만료되지 않았는지 (true: 만료되지 않음)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화되어 있는지 (true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> user.getRoleKey());

        return collection;
    }
}