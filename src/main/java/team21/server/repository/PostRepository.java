package team21.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team21.server.domain.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select distinct p from Post p left join fetch p.comments c")
    List<Post> findAllWithFetchComments();
}
