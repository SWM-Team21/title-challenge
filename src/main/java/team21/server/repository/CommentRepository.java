package team21.server.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team21.server.domain.Comment;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void save(Comment comment) {
        em.persist(comment);
    }

    public Comment findOneById(Long id) {
        return em.find(Comment.class, id);
    }

    public List<Comment> getCommentsOfUser(Long userId) {
        return em.createQuery("select c from Comment c " +
                        "join fetch c.user u " +
                        "where u.id = :userId", Comment.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public void delete(Long commentId) {
        em.createQuery("delete from Comment c where c.id = :commentId")
                .setParameter("commentId", commentId)
                .executeUpdate();
        em.flush();
        em.clear();
    }

}
