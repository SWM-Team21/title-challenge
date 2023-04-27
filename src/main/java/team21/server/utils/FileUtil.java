package team21.server.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class FileUtil {

    @Value("${imagePath.users}")
    private String userImagePath;

    @Value("${imagePath.posts}")
    private String postImagePath;


    public String uploadUserImage(MultipartFile file, long userId) throws IOException {
        String imageName = file.getOriginalFilename();
        file.transferTo(new File(userImagePath + LocalDateTime.now() + '_' + userId + '-' + imageName));

        return imageName;
    }

    public String uploadPostImage(MultipartFile file, long userId) throws IOException {
        String imageName = file.getOriginalFilename();
        file.transferTo(new File(postImagePath + LocalDateTime.now() + '_' + userId + '-' + imageName));

        return imageName;
    }
}
