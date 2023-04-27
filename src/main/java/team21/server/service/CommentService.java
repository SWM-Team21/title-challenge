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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    @Transactional
    public Comment save(Boolean anonymous, String body, Long userId, Long postId) {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);
        Comment comment = new Comment(anonymous, body, user, post);
        post.getComments().add(comment);
        commentRepository.save(comment);

        return comment;
    }

    public List<Comment> findCommentsWithPost(Long postId) {
        return commentRepository.getCommentsOfPost(postId);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.delete(commentId);
    }

}
