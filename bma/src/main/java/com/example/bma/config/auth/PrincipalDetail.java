package com.example.bma.config.auth;

import com.example.bma.domain.admin.Admin;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/*
  스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
  스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다.

  UserDatails 객체 상속시 시큐리티의 고유한 세션저장소에 저장을 할 수 있게 된다.
  스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
  스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다.
*/
@RequiredArgsConstructor
@Component
@Getter
// UserDatails 객체 상속시 시큐리티의 고유한 세션저장소에 저장을 할 수 있게 된다.
public class PrincipalDetail implements UserDetails {

    private Admin admin; // 콤포지션 : 객체를 품고 있는 것


    public void setUser(Admin admin) {
        this.admin = admin;
    }

    public PrincipalDetail(Admin admin) {
        this.admin = admin;
    }

    /**
     * 사용자 구분코드 (PK)
     * @return
     */
    public Long getId() {
        return admin.getId();
    }

    /**
     * 사용자 아이디
     * @return
     */
    @Override
    public String getUsername() {
        return admin.getAdminname();
    }

    /**
     * 사용자 비밀번호
     * @return
     */
    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    /**
     * 사용자 명 
     * @return
     */
    public String getName(){
        return admin.getName();
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
        collection.add(() -> admin.getRoleKey());

        return collection;
    }
}