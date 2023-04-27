package team21.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team21.server.domain.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p join fetch Comment c where p.postId = c.post.postId order by p.createdAt desc")
    List<Post> findAllByNewestDesc();

    @Query("select p from Post p join fetch Comment c where p.postId = c.post.postId order by count(c.likes)") // TODO 쿼리수정
    List<Post> findAllByPopularityDesc();
}
