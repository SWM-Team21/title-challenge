package team21.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import team21.server.auth.details.UserDetailsImpl;
import team21.server.domain.User;
import team21.server.dto.UserDto;
import team21.server.mapper.UserMapper;
import team21.server.service.UserService;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody UserDto.Signup signupDto) {
        userService.createUser(mapper.signupToEntity(signupDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/image")
    public ResponseEntity updateUserImage(@Valid @RequestBody MultipartFile file,
                                          @AuthenticationPrincipal UserDetailsImpl principal) throws IOException {
        long userId = principal.getUserId();
        userService.updateImage(userId, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/mypage")
    public ResponseEntity getMyPage(@AuthenticationPrincipal UserDetailsImpl principal) {
        long userId = principal.getUserId();
        User user = userService.findUserById(userId);
        UserDto.MyPage myPageDto = mapper.entityToMyPage(user);
        byte[] image = userService.getUserImage(userId);
        myPageDto.setImage(image);

        return new ResponseEntity<>(myPageDto, HttpStatus.OK);
    }
}
