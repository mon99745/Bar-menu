package danta.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // 시큐리티 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean //@Bean을 통해 비밀번호 암호화 스프링 부트 2.0부터는 필수
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String[] PERMIT_URL_ARRAY = {

            /* swagger v3 */
//            "/swagger-ui.html",
            "/swagger-resources/**",
            "/api",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //csrf 토큰 해제
                .authorizeRequests() // URL별 권한 관리를 설정하는 옵션
                .antMatchers(PERMIT_URL_ARRAY).permitAll()
                .antMatchers("/","/static/**", "/auth/**", "/js/**", "/css/**", "/img/**", "/error/**").permitAll() //권한 관리 대상을 지정하는 옵션

                .antMatchers("/chat/**","/product/**", "/cart/**", "/order/**", "/my/order/**").permitAll() // 게스트를 위한 개방
                .anyRequest().authenticated()
                .and()

                .formLogin() //권한이 없는 사람이 페이지를 이동하려고 하면 로그인 페이지로 이동
                .loginPage("/auth/user/login") //해당하는 로그인 페이지 URL로 이동

                //loginProcessingUrl에 form의 action url을 여기다 적어줍니다.
                ///auth/user/login이 URL의 API Controller를 작성하지 않는 이유는 스프링 시큐리티가 얘를 가로채서 대신 작업을 수행해줍니다.
                .loginProcessingUrl("/auth/user/login") //시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/auth/user/login")
                .invalidateHttpSession(true); //세션 날리기

//                .defaultSuccessUrl("/"); //로그인이 성공하면 해당 URL로 이동

        // 보류 remember.me
        http
                .rememberMe().tokenValiditySeconds(60 * 60 * 7); // 쿠키를 얼마나 유지할 것인지 계산합니다. (7일 설정)

    }

    @Override
    // 회원수정 후에 세션을 유지하기 위해 코드를 수정
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}