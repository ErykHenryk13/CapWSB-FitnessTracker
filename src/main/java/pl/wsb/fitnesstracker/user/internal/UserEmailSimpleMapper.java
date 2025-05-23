package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserEmailSimpleDTO;

@Component
class UserEmailSimpleMapper
{
    /**
     * Maps User entity to UserEmailSimpleDto
     * @param user User
     * @return UserEmailSimpleDto
     */
    UserEmailSimpleDTO toEmailSimpleDto(User user)
    {
        return new UserEmailSimpleDTO(user.getId(), user.getEmail());
    }

    /**
     * Maps UserEmailSimpleDto to User entity
     * @param userDto UserEmailSimpleDto
     * @return User
     */
    User toSimpleEmailEntity(UserEmailSimpleDTO userDto)
    {
        return new User(null, null, null, userDto.email());
    }
}
