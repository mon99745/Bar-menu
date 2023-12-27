package com.example.bma.service.admin;

import com.example.bma.domain.admin.Admin;
import com.example.bma.domain.admin.AdminRepository;
import com.example.bma.model.enums.Role;
import com.example.bma.model.enums.Status;
import com.example.bmc.exception.BmaError;
import com.example.bmc.exception.BmcException;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 관리자 가입
     */
    @Transactional
    public Long save(Admin admin) {
        if (adminRepository.findByAdminname(admin.getAdminname()) != null) {
            throw new BmcException(BmaError.BMA_ID_DUPLICATE, null);
        }
        admin.setRole(Role.ADMIN);
        admin.setStatus(Status.TRUE);

        // 패스워드를 암호화해서 저장
        String hashPw = bCryptPasswordEncoder.encode(admin.getPassword());
        admin.setPassword(hashPw);

        adminRepository.save(admin);

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
     * 전체 관리자 목록 조회
     */
    public List<Admin> findAllAdmin() {
        return adminRepository.findAll();
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
                -> new BmcException(BmaError.BMA_ID_NOT_EXIST, "ID = " + admin.getId()));

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
    private Admin validateExistMember(Optional<Admin> admin) {
        if(!admin.isPresent()){
            throw new BmcException(BmaError.BMA_ADMIN_INFO_NOT_EXIST, null);
        }
        return admin.get();
    }
}
