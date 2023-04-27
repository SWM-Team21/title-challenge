package team21.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team21.server.auth.details.UserDetailsImpl;
import team21.server.domain.User;
import team21.server.service.LikeService;
import team21.server.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final UserService userService;
    private final LikeService likeService;

    @PostMapping("/{postId}")
    public ResponseEntity<?> likes(@PathVariable("postId") Long postId,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUserId();
        User currentUser = userService.findUserById(userId);

        likeService.save(postId, currentUser.getUserId());

        return new ResponseEntity<>("좋아요 성공", HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> unLikes(@PathVariable("postId") Long postId,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUserId();
        User currentUser = userService.findUserById(userId);

        likeService.delete(postId, currentUser.getUserId());

        return new ResponseEntity<>("좋아요 취소 성공", HttpStatus.OK);
    }

}
