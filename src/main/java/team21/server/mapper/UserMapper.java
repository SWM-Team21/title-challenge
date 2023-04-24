package team21.server.mapper;

import org.mapstruct.Mapper;
import team21.server.domain.User;
import team21.server.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User signupToEntity(UserDto.Signup signupDto);
}
