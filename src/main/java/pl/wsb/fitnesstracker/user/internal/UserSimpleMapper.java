package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserSimpleDTO;

@Component
class UserSimpleMapper
{
    /**
     * Maps User entity to UserSimpleDto
     * @param user User
     * @return UserSimpleDto
     */
    UserSimpleDTO toSimpleDto(User user)
    {
        return new UserSimpleDTO(user.getId(), user.getFirstName(), user.getLastName());
    }

    /**
     * Maps UserSimpleDto to User entity
     * @param userDto UserSimpleDto
     * @return User
     */
    User toSimpleEntity(UserSimpleDTO userDto)
    {
        return new User(userDto.firstName(), userDto.lastName(), null, null);
    }
}
