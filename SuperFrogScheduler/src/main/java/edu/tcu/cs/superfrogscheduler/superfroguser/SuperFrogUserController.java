package edu.tcu.cs.superfrogscheduler.superfroguser;

import edu.tcu.cs.superfrogscheduler.superfroguser.converter.UserDtoToUserConverter;
import edu.tcu.cs.superfrogscheduler.superfroguser.converter.UserToUserDtoConverter;
import edu.tcu.cs.superfrogscheduler.superfroguser.dto.UserDto;
import edu.tcu.cs.superfrogscheduler.system.HttpStatusCode;
import edu.tcu.cs.superfrogscheduler.system.Result;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/users")
public class SuperFrogUserController {
    private final SuperFrogUserService superFrogUserService;

    private final UserToUserDtoConverter userToUserDtoConverter;

    private final UserDtoToUserConverter userDtoToUserConverter;

    public SuperFrogUserController(SuperFrogUserService superFrogUserService, UserToUserDtoConverter userToUserDtoConverter, UserDtoToUserConverter userDtoToUserConverter) {
        this.superFrogUserService = superFrogUserService;
        this.userToUserDtoConverter = userToUserDtoConverter;
        this.userDtoToUserConverter = userDtoToUserConverter;
    }

    @GetMapping
    public Result findAllUsers() {
        List<SuperFrogUser> foundUsers = this.superFrogUserService.findAll();

        // Convert foundUsers to a list of UserDtos.
        List<UserDto> userDtos = foundUsers.stream()
                .map(this.userToUserDtoConverter::convert)
                .collect(Collectors.toList());

        // Note that UserDto does not contain password field.
        return new Result(true, HttpStatusCode.SUCCESS, "Find All Success", userDtos);
    }

    @GetMapping("/{userId}")
    public Result findUserById(@PathVariable Integer userId) {
        SuperFrogUser foundUser = this.superFrogUserService.findById(userId);
        UserDto userDto = this.userToUserDtoConverter.convert(foundUser);
        return new Result(true, HttpStatusCode.SUCCESS, "Find One Success", userDto);
    }


    @PostMapping
    public Result addUser(@Valid @RequestBody SuperFrogUser newUser) {
        SuperFrogUser savedUser = this.superFrogUserService.save(newUser);
        UserDto savedUserDto = this.userToUserDtoConverter.convert(savedUser);
        return new Result(true, HttpStatusCode.SUCCESS, "Add Success", savedUserDto);
    }

    // We are not using this to update password, need another changePassword method in this class.
    @PutMapping("/{userId}")
    public Result updateUser(@PathVariable Integer userId, @Valid @RequestBody UserDto userDto) {
        SuperFrogUser update = this.userDtoToUserConverter.convert(userDto);
        SuperFrogUser updatedUser = this.superFrogUserService.update(userId, update);
        UserDto updatedUserDto = this.userToUserDtoConverter.convert(updatedUser);
        return new Result(true, HttpStatusCode.SUCCESS, "Update Success", updatedUserDto);
    }

    @DeleteMapping("/{userId}")
    public Result deleteUser(@PathVariable Integer userId) {
        this.superFrogUserService.delete(userId);
        return new Result(true, HttpStatusCode.SUCCESS, "Delete Success");
    }

    @PutMapping("/{username}/disable")
    public Result disableUser(@PathVariable String username) {
        SuperFrogUser updatedUser = this.superFrogUserService.disableUser(username);
        UserDto updatedUserDto = this.userToUserDtoConverter.convert(updatedUser);
        return new Result(true, HttpStatusCode.SUCCESS, "Disable User Success", updatedUserDto);
    }

    @PutMapping("/{username}/enable")
    public Result enableUser(@PathVariable String username) {
        SuperFrogUser updatedUser = this.superFrogUserService.enableUser(username);
        UserDto updatedUserDto = this.userToUserDtoConverter.convert(updatedUser);
        return new Result(true, HttpStatusCode.SUCCESS, "Enable User Success", updatedUserDto);
    }

}
