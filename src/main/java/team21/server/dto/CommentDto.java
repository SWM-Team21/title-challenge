package team21.server.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class CommentDto {

    private Long id;

    private Boolean anonymous;

    private Long userId;

    private Long postId;

    private String nickName;

    private String imageName;

    @NotBlank
    private String body;

    private Long likeCount;

}
