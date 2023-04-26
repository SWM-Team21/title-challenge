package team21.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team21.server.auth.details.UserDetailsImpl;
import team21.server.domain.Comment;
import team21.server.domain.User;
import team21.server.dto.CommentDto;
import team21.server.service.CommentService;
import team21.server.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentDto commentDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUserId();
        User user = userService.findUserById(userId);

        Comment comment = commentService.save(commentDto.getAnonymous(),
                commentDto.getBody(),
                user.getUserId(),
                commentDto.getPostId());
        commentDto.setId(comment.getId());
        commentDto.setNickName(user.getNickName());
        commentDto.setImageName(user.getImageName());
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("댓글 삭제 성공", HttpStatus.OK);
    }

}
