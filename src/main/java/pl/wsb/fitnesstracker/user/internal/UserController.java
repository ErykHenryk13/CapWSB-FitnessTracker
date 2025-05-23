package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserEmailSimpleDTO;
import pl.wsb.fitnesstracker.user.api.UserSimpleDTO;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController
{

    private final UserServiceImpl userService;
    private final UserMapper userMapper;
    private final UserSimpleMapper userSimpleMapper;
    private final UserEmailSimpleMapper userEmailSimpleMapper;

    /**
     * Get all users
     *
     * @return List of UserDto
     */
    @GetMapping
    public List<UserDto> getAllUsers()
    {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Get all users in simple format
     *
     * @return List of UserSimpleDto
     */
    @GetMapping("/simple")
    public List<UserSimpleDTO> getAllUsersSimple()
    {
        return userService.findAllUsers()
                .stream()
                .map(userSimpleMapper::toSimpleDto)
                .toList();
    }

    /**
     * Get user by ID
     *
     * @param userId Long
     * @return UserDto
     */
    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId)
    {
        return userService.getUser(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User with Id: " + userId + "was not found"));
    }

    /**
     * Get user by email
     *
     * @param email String
     * @return List of UserEmailSimpleDto
     */
    @GetMapping("/email")
    public List<UserEmailSimpleDTO> getUserByEmail(@RequestParam String email)
    {
        return userService.getUserByEmailIgnoreCase(email)
                .stream()
                .map(userEmailSimpleMapper::toEmailSimpleDto)
                .toList();
    }

    /**
     * Get users older than a given date
     *
     * @param time LocalDate
     * @return List of UserDto
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate time)
    {
        return userService.getUsersOlderThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Add a new user
     *
     * @param userDto UserDto
     * @return User
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException
    {
        try
        {
            User user = userMapper.toEntity(userDto);
            userService.createUser(user);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Can't add user with email: " + userDto.email() + "error: " + e.getMessage());
        }

        return null;
    }

    /**
     * Update an existing user
     *
     * @param userId Long
     * @param userDto UserDto
     * @return User
     */
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto userDto)
    {
        try
        {
            User foundUser = userService.getUser(userId).orElseThrow(() -> new IllegalArgumentException("User with Id: " + userId + "was not found"));
            User updatedUser = userMapper.toUpdateEntity(userDto, foundUser);

            return userService.updateUser(updatedUser);
        } catch (Exception e)
        {
            throw new IllegalArgumentException("Can't update user with Id: " + userId + " error: " + e.getMessage());
        }
    }

    /**
     * Delete an existing user
     *
     * @param userId Long
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId)
    {
        try
        {
            userService.deleteUser(userId);
        } catch (Exception e)
        {
            throw new IllegalArgumentException("Can't delete user with ID: " + userId + "error: " + e.getMessage());
        }
    }
}