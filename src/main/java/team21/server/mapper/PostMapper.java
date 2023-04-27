package team21.server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import team21.server.domain.Post;
import team21.server.dto.PostDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mappings({
            @Mapping(target = "userId", source = "user.userId"),
            @Mapping(target = "userImageName", source = "user.imageName"),
            @Mapping(target = "postImageName", source = "imageName")
    })
    PostDto entityToPostDto(Post post);

    List<PostDto> entitiesToPostDtos(List<Post> posts);
}
