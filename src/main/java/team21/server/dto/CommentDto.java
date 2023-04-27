package team21.server.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class CommentDto {

    private Long id;

    private Boolean anonymous;

    private Long postId;

    private String nickName;

    private String imageName;

    @NotBlank
    private String body;

    private Long likeCount;

    public CommentDto(Boolean anonymous, Long postId, String nickName, String imageName, String body, Long likeCount) {
        this.anonymous = anonymous;
        this.postId = postId;
        this.nickName = nickName;
        this.imageName = imageName;
        this.body = body;
        this.likeCount = likeCount;
    }

    @Getter
    @Setter
    public static class Response {
        private Long commentId;
        private String nickName;
        private String body;
        private Long likeCount;
    }

}
