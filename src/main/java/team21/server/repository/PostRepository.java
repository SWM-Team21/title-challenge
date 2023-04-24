package team21.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team21.server.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
