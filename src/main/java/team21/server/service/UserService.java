package team21.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team21.server.aop.BusinessLogicException;
import team21.server.auth.utils.UserAuthorityUtil;
import team21.server.domain.User;
import team21.server.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthorityUtil userAuthorityUtil;

    public void createUser(User user) {
        verifyDuplicatedUserIdExists(user.getUserId());
        verifyDuplicatedNickNameExists(user.getNickName());

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRole(userAuthorityUtil.createRoles());

        userRepository.save(user);
    }

    private void verifyDuplicatedUserIdExists(String userId) {
        if (userRepository.existsByUserId(userId)) {
            throw new BusinessLogicException("중복된 아이디입니다.");
        }
    }

    private void verifyDuplicatedNickNameExists(String nickName) {
        if (userRepository.existsByNickName(nickName)) {
            throw new BusinessLogicException("중복된 닉네임입니다.");
        }
    }
}
