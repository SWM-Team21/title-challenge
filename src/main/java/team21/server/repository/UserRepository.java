package team21.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team21.server.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
}
