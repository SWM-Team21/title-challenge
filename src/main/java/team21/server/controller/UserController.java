package team21.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team21.server.dto.UserDto;
import team21.server.mapper.UserMapper;
import team21.server.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserDto.Signup signupDto) {
        userService.createUser(mapper.signupToEntity(signupDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
