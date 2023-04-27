package team21.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team21.server.aop.BusinessLogicException;
import team21.server.auth.utils.UserAuthorityUtil;
import team21.server.domain.User;
import team21.server.repository.UserRepository;
import team21.server.utils.FileUtil;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthorityUtil userAuthorityUtil;
    private final FileUtil fileUtil;

    public void createUser(User user) {
        verifyDuplicatedLoginIdExists(user.getLoginId());
        verifyDuplicatedNickNameExists(user.getNickName());

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRole(userAuthorityUtil.createRoles());

        userRepository.save(user);
    }

    private void verifyDuplicatedLoginIdExists(String loginId) {
        if (userRepository.existsByLoginId(loginId)) {
            throw new BusinessLogicException("중복된 아이디입니다.");
        }
    }

    private void verifyDuplicatedNickNameExists(String nickName) {
        if (userRepository.existsByNickName(nickName)) {
            throw new BusinessLogicException("중복된 닉네임입니다.");
        }
    }

    public void updateImage(long userId, MultipartFile file) throws IOException {
        User user = findUserById(userId);
        String imageName = fileUtil.uploadUserImage(file, userId);
        user.setImageName(imageName);

        userRepository.save(user);
    }

    public User findUserById(long userId) {
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        return optionalUser.orElseThrow(() -> new BusinessLogicException("존재하지 않은 아이디입니다."));
    }
}
