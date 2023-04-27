package team21.server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import team21.server.domain.User;
import team21.server.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "userId", ignore = true)
    User signupToEntity(UserDto.Signup signupDto);
}
