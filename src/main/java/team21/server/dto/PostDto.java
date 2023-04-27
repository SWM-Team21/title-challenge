package team21.server.dto;

import lombok.Getter;
import lombok.Setter;
import team21.server.domain.Comment;

import java.util.List;

@Getter
@Setter
public class PostDto {
    private Long userId;

    private String userImageName;

    private Long postId;

    private String postImageName;

    private List<CommentDto> comments;
}
