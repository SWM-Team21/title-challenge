package team21.server.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team21.server.domain.Like;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LikeRepository {

    private final EntityManager em;

    public void save(Like like) {
        em.persist(like);
    }

    public Like findOneById(Long id) {
        return em.find(Like.class, id);
    }

    public List<Like> findLikesWithComment(Long commentId) {
        return em.createQuery("select l from Like l " +
                        "join fetch l.comment c " +
                        "where c.id = :commentId", Like.class)
                .setParameter("commentId", commentId)
                .getResultList();
    }

    public void deleteLike(Long likeId) {
        em.createQuery("delete from Like l where l.id = :likeId")
                .setParameter("likeId", likeId)
                .executeUpdate();
        em.flush();
        em.clear();
    }

    public Like findLike(Long commentId, Long userId) {
        List<Like> likes = em.createQuery("select l from Like l " +
                        "join fetch l.user u, l.comment c " +
                        "where u.id = :userId and c.id = :commentId", Like.class)
                .setParameter("commentId", commentId)
                .setParameter("userId", userId)
                .getResultList();

        return likes.get(0);
    }
}
