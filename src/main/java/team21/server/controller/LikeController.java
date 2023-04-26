package team21.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team21.server.auth.details.UserDetailsImpl;
import team21.server.domain.Post;
import team21.server.domain.User;
import team21.server.service.CommentService;
import team21.server.service.LikeService;
import team21.server.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final UserService userService;
//    private final PostService postService;
    private final LikeService likeService;
    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<?> likes(@PathVariable("postId") Long postId,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUserId();
        User currentUser = userService.findUserById(userId);

        likeService.likes(postId, currentUser.getUserId());

//        Post post = postService.findOneById(postId);
//        if(!post.getUser().getId().equals(currentUser.getId()))
//            notificationService.save(post.getUser(), currentUser, post.getImageUrl(), NotificationStatus.LIKE, postId);

        return new ResponseEntity<>("좋아요 성공", HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> unLikes(@PathVariable("postId") Long postId,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUserId();
        User currentUser = userService.findUserById(userId);

        likeService.unLikes(postId, currentUser.getUserId());

//        Post post = postService.findOneById(postId);
//        if(!post.getUser().getId().equals(currentUser.getId()))
//            notificationService.cancel(currentUser.getId(), NotificationStatus.LIKE, postId);

        return new ResponseEntity<>("좋아요 취소 성공", HttpStatus.OK);
    }

}
