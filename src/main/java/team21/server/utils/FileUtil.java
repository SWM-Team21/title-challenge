package team21.server.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class FileUtil {

    @Value("${imagePath.users}")
    private String userImagePath;

    @Value("${imagePath.posts}")
    private String postImagePath;

    @Value("${spring.servlet.multipart.location}")
    private String fullPath;


    public String uploadUserImage(MultipartFile file, long userId) throws IOException {
        String imageName = userImagePath + userId + '-' + file.getOriginalFilename();
        file.transferTo(new File(imageName));

        return imageName;
    }

    public String uploadPostImage(MultipartFile file, long userId) throws IOException {
        String imageName = file.getOriginalFilename();
        file.transferTo(new File(postImagePath + userId + '-' + imageName));

        return imageName;
    }

    public byte[] downloadImage(String imageName) {
        try {
            return Files.readAllBytes(new File(fullPath + imageName).toPath());
        } catch (IOException e) {
            return null;
        }
    }

    public String downloadImageByPath(String imageName) {
        return fullPath + imageName;
    }
}
