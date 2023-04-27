package team21.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import team21.server.aop.BusinessLogicException;
import team21.server.domain.Post;
import team21.server.domain.User;
import team21.server.repository.PostRepository;
import team21.server.utils.FileUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final FileUtil fileUtil;


    public Post createPost(long userId, MultipartFile file) throws IOException {
        User user = userService.findUserById(userId);
        String imageName = fileUtil.uploadPostImage(file, userId);

        Post newPost = new Post();
        newPost.setUser(user);
        newPost.setImageName(imageName);

        return postRepository.save(newPost);
    }

    public void deletePost(long userId, long postId) {
        Post post = findPostById(postId);
        verifyPostOwner(userId, post);
        postRepository.delete(post);
    }

    private void verifyPostOwner(long userId, Post post) {
        User user = userService.findUserById(userId);
        if (!post.getUser().equals(user)) {
            throw new BusinessLogicException("게시글 소유자가 아닙니다.");
        }
    }

    public Post findPostById(long postId) {
        // TODO 이미지 가져오기
        Optional<Post> optionalPost = postRepository.findById(postId);
        return optionalPost.orElseThrow(() -> new BusinessLogicException("존재하지 않은 게시글입니다."));
    }

    public List<Post> findAllByCreatedAtDesc() {
        // TODO
        return null;
    }

    public List<Post> findAllByPopularityDesc() {
        // TODO
        return null;
    }

}
