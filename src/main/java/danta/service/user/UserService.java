package danta.service.user;

import danta.domain.cart.Cart;
import danta.domain.user.User;
import danta.domain.user.UserRepository;
import danta.model.enums.Role;
import danta.model.enums.Status;
import danta.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final CartService cartService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원 가입
     */
    @Transactional
    public Long save(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("중복된 아이디 입니다.");
        }
        user.setRole(Role.USER);

        // 패스워드를 암호화해서 저장
        String hashPw = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPw);

        // 활성화 상태
        user.setStatus(Status.TRUE);

        userRepository.save(user);

        // 장바구니 생성
        cartService.createCart(user.getId());

        return userRepository.save(user).getId();
    }

    /**
     * 회원 정보 조회
      * @param id
     * @return
     */
    public User findUser(Long id) {
        User user = validateExistMember(userRepository.findById(id));

        return user;
    }

    /**
     * 전체 회원 목록 (관리자용)
     */
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    /**
     * 회원 정보 수정
     */
    @Transactional
    public User update(Long userId, User user) {
        // 패스워드를 암호화해서 저장
        String hashPw = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPw);

        User tempUser = userRepository.findById(userId).orElseThrow(()
                -> new IllegalArgumentException("해당 회원이 없습니다. id=" + user.getId()));

        tempUser.setPassword(user.getPassword());
        tempUser.setName(user.getName());
        tempUser.setStatus(user.getStatus());

        return tempUser;
    }

    /**
     * 회원 삭제 로직
     */
    @Transactional
    public void delete(Long userId, User user) {
        user.setId(userId);
        userRepository.delete(user);
    }

    /**
     * 회원 예외검증
     */
    private User validateExistMember(Optional<User> memberEntity) {
        if(!memberEntity.isPresent())
            throw new IllegalStateException("존재하지 않는 유저입니다.");
        return memberEntity.get();
    }

}