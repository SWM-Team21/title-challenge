package team21.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team21.server.domain.Comment;
import team21.server.domain.Post;
import team21.server.domain.User;
import team21.server.repository.CommentRepository;
import team21.server.repository.PostRepository;
import team21.server.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public Comment save(Boolean anonymous, String body, Long userId, Long postId) {
        User user = userRepository.findUserById(userId);
        Post post = postRepository.findPostById(postId);
        Comment comment = new Comment(anonymous, body, user, post);
        post.getComments().add(comment);
        commentRepository.save(comment);

        return comment;
    }

    public List<Comment> findCommentsWithUser(Long userId) {
        return commentRepository.getCommentsOfUser(userId);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.delete(commentId);
    }

}
