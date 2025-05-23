package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;

@Component
public class UserMapper {

    /**
     * Maps User entity to UserDto
     * @param user User
     * @return UserDto
     */
    public UserDto toDto(User user)
    {
        if (user == null)
        {
            return null;
        }
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * Maps UserDto to User entity
     * @param userDto UserDto
     * @return User
     */
    public User toEntity(UserDto userDto)
    {
        if (userDto == null)
        {
            return null;
        }
        return new User(userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

    /**
     * Maps UserDto to User entity with state of previous entity
     * @param userDto UserDto
     * @param user User
     *
     * @return User
     */
    public User toUpdateEntity(UserDto userDto, User user)
    {
        if (userDto == null)
        {
            return user;
        }

        if (userDto.firstName() != null)
        {
            user.setFirstName(userDto.firstName());
        }

        if (userDto.lastName() != null)
        {
            user.setLastName(userDto.lastName());
        }

        if (userDto.birthdate() != null)
        {
            user.setBirthdate(userDto.birthdate());
        }

        if (userDto.email() != null)
        {
            user.setEmail(userDto.email());
        }

        return user;
    }
}
