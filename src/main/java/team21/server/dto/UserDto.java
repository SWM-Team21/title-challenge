package team21.server.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {
    @Getter
    @Setter
    public static class Signup {
        @NotBlank
        private String loginId;

        @NotBlank
        @Size(max = 15)
        private String nickName;

        @NotBlank
        private String password;
    }

    @Getter
    @Setter
    public static class Login {
        @NotBlank
        private String loginId;

        @NotBlank
        private String password;
    }

}
