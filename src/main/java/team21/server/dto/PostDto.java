package team21.server.dto;

import lombok.Getter;
import lombok.Setter;
import team21.server.domain.Comment;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostDto {
    private Long userId;

    private byte[] userImage;

    private Long postId;

    private byte[] postImage;

    private List<CommentDto.Response> comments;
}
