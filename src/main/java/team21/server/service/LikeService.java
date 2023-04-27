package team21.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team21.server.domain.Comment;
import team21.server.domain.Like;
import team21.server.domain.User;
import team21.server.repository.CommentRepository;
import team21.server.repository.LikeRepository;
import team21.server.repository.PostRepository;
import team21.server.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;

    public List<Like> findLikesWithUser(Long userId) {
        return likeRepository.findLikesWithUser(userId);
    }

    @Transactional
    public void likes(Long userId, Long commentId) {
        User user = userService.findUserById(userId);
        Comment comment = commentRepository.findOneById(commentId);
        Like like = new Like(user, comment);

        likeRepository.save(like);
    }

    @Transactional
    public void unLikes(Long userId, Long commentId) {
        Like like = likeRepository.findLike(commentId, userId);
        likeRepository.deleteLike(like.getId());
    }

}
