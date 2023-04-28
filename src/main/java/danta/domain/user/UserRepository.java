package danta.domain.user;

import danta.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Long> {
    User findByUsername(String username);
}