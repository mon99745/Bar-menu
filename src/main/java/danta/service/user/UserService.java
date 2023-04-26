package danta.service.user;

import danta.model.Cart;
import danta.model.User;
import danta.repository.UserRepository;
import danta.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor //생성자 주입을 받기 위해
@Service
public class UserService {

    private final UserRepository userRepository;
    private final CartService cartService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원가입 로직
     */
    @Transactional
    public Long save(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("중복된 아이디 입니다.");
        }
        // 패스워드를 암호화해서 저장
        String hashPw = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPw);

        // 회원가입과 동시에 장바구니 생성
        User savedUser = userRepository.save(user);
        Cart cart = new Cart();
        cart.setId(savedUser.getId());

        cartService.createCart(cart);

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
     * 전체 회원 목록 로직 (관리자용)
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 회원 정보 수정
     */
    @Transactional
    public User update(Long userId, User user) {
        User tempUser = userRepository.findById(userId).orElseThrow(()
                -> new IllegalArgumentException("해당 회원이 없습니다. id=" + user.getId()));

        tempUser.setPassword(user.getPassword());
        tempUser.setName(user.getName());
        tempUser.setStatus(user.getStatus());

        return tempUser;
    }

    /**
     * 회원삭제 로직
     */
    @Transactional
    public void delete(User user) {
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