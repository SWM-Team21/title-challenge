package team21.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team21.server.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
