package com.example.bma.cotroller.admin;

import com.example.bma.domain.admin.Admin;
import com.example.bma.service.admin.AdminService;
import com.example.bmc.util.HttpClient;
import com.example.bmc.vaildation.DefaultProperties;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Swagger 사용을 위해 모든 권한(authh) 을 허용한 상태
 */

@Slf4j
@Api(tags = AdminRestController.TAG)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RestController
@RequestMapping(AdminRestController.PATH)
public class AdminRestController {

    @Value("${security.auth}")
    private boolean auth;
    public static final String PATH = "/auth/api/v1/admin";
    public static final String TAG = "Admin Rest API";
    private final AdminService adminService;

    protected final DefaultProperties defaultProperties;


    /**
     * 관리자 가입 API
     */
    @Operation(summary = "1. 관리자 가입")
    @PostMapping("create")
    public Admin create(@RequestBody Admin createMsg) {
        adminService.save(createMsg);
        return createMsg;
    }

    /**
     * 관리자 조회 API
     */
    @Operation(summary = "2. 관리자 조회")
    @GetMapping("read/{adminId}")
    public Admin read(@PathVariable Long adminId) {
        return adminService.findAdmin(adminId);
    }

    /**
     * 전체 회원 조회 API (관리자용)
     */
    @Operation(summary = "5. 전체 회원 조회 (관리자용)")
    @GetMapping("userList")
    public String userList(Model model){
        model.addAttribute(HttpClient.sendGet(defaultProperties.getBmmReadUri()));
        return "admin/user-list";
    }

    /**
     * 관리자 수정 API
     */
    @Operation(summary = "3. 관리자 정보 수정")
    @PostMapping("update/{adminId}")
    public Admin update(@PathVariable Long adminId, @RequestBody Admin updateMsg) {
        adminService.update(adminId, updateMsg);
        return updateMsg;
    }

    /**
     * 관리자 탈퇴 API
     */
    @Operation(summary = "4. 관리자 탈퇴 ")
    @PostMapping("delete/{adminId}")
    public void delete(@PathVariable Long adminId, @RequestBody Admin admin) {
        adminService.delete(adminId, admin);
    }

}
