package edu.tcu.cs.superfrogscheduler.superfroguser.converter;

import edu.tcu.cs.superfrogscheduler.superfroguser.SuperFrogUser;
import edu.tcu.cs.superfrogscheduler.superfroguser.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, SuperFrogUser> {
    @Override
    public SuperFrogUser convert(UserDto source) {
        SuperFrogUser user = new SuperFrogUser();
        user.setUsername(source.username());
        user.setEmail(source.email());
        user.setActive(source.active());
        user.setRoles(source.roles());
        return user;
    }
}
