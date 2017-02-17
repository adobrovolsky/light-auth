package dobrovolsky.server.domain.mapper;

import dobrovolsky.server.domain.User;
import dobrovolsky.shared.UserDto;

public class UserMapper implements Mapper<User, UserDto> {

    @Override
    public UserDto map(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }
}
