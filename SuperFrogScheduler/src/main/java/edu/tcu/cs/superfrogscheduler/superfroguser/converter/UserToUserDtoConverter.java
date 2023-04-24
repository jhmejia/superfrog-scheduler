package edu.tcu.cs.superfrogscheduler.superfroguser.converter;

import edu.tcu.cs.superfrogscheduler.superfroguser.SuperFrogUser;
import edu.tcu.cs.superfrogscheduler.superfroguser.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<SuperFrogUser, UserDto> {
    @Override
    public UserDto convert(SuperFrogUser source) {
        final UserDto userDto = new UserDto(
                source.getId(),
                source.getEmail(),
                source.getUsername(),
                source.isActive(),
                source.getRoles());
        return userDto;
    }
}
