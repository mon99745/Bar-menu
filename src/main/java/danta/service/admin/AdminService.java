package danta.service.admin;

import danta.model.Admin;
import danta.model.User;
import danta.repository.AdminRepository;
import danta.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 관리자 가입
     */
    @Transactional
    public Long save(Admin admin) {
        if (adminRepository.findByAdminname(admin.getAdminname()) != null) {
            throw new IllegalArgumentException("중복된 아이디 입니다.");
        }
        // 패스워드를 암호화해서 저장
        String hashPw = bCryptPasswordEncoder.encode(admin.getPassword());
        admin.setPassword(hashPw);

        return adminRepository.save(admin).getId();
    }

    /**
     * 관리자 정보 조회
     * @param adminId
     * @return
     */
    public Admin findAdmin(Long adminId) {
        Admin admin = validateExistMember(adminRepository.findById(adminId));

        return admin;
    }

    /**
     * 전체 회원 목록 로직 (관리자용)
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 관리자 정보 수정
     */
    @Transactional
    public Admin update(Long adminId, Admin admin) {
        // 패스워드를 암호화해서 저장
        String hashPw = bCryptPasswordEncoder.encode(admin.getPassword());
        admin.setPassword(hashPw);

        Admin tempAdmin = adminRepository.findById(adminId).orElseThrow(()
                -> new IllegalArgumentException("해당 회원이 없습니다. id=" + admin.getId()));

        tempAdmin.setPassword(admin.getPassword());
        tempAdmin.setName(admin.getName());
        tempAdmin.setStatus(admin.getStatus());
        tempAdmin.setAddress(admin.getAddress());

        return tempAdmin;
    }

    /**
     * 회원삭제 로직
     */
    @Transactional
    public void delete(Long adminId, Admin admin) {
        admin.setId(adminId);
        adminRepository.delete(admin);
    }


    /**
     * 회원 예외검증
     */
    private Admin validateExistMember(Optional<Admin> memberEntity) {
        if(!memberEntity.isPresent())
            throw new IllegalStateException("존재하지 않는 유저입니다.");
        return memberEntity.get();
    }
}
