package edu.tcu.cs.superfrogscheduler.security;


import edu.tcu.cs.superfrogscheduler.superfroguser.MyUserPrincipal;
import edu.tcu.cs.superfrogscheduler.superfroguser.SuperFrogUser;
import edu.tcu.cs.superfrogscheduler.superfroguser.converter.UserToUserDtoConverter;
import edu.tcu.cs.superfrogscheduler.superfroguser.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final JwtProvider jwtProvider;

    private final UserToUserDtoConverter userToUserDtoConverter;

    public AuthService(JwtProvider jwtProvider, UserToUserDtoConverter userToUserDtoConverter) {
        this.jwtProvider = jwtProvider;
        this.userToUserDtoConverter = userToUserDtoConverter;
    }

    public Map<String,Object> createLoginInfo(Authentication authentication) {
        // Create user info.
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
        SuperFrogUser user = principal.getUser();
        UserDto userDto = this.userToUserDtoConverter.convert(user);
        // Create a JWT.
        String token = this.jwtProvider.createToken(authentication);

        Map<String, Object> loginResultMap = new HashMap<>();

        loginResultMap.put("userInfo", userDto);
        loginResultMap.put("token", token);

        return loginResultMap;
    }
}


