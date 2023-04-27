package danta.repository;

import danta.model.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByAdminname(String username);
}