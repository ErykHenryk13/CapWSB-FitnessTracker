package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserProvider
{

    private final UserRepository userRepository;

    /**
     * Create a new User
     *
     * @param user User
     * @return
     */
    @Override
    public User createUser(final User user)
    {
        log.info("Creating new User {}", user);
        if (user.getId() != null)
        {
            throw new IllegalArgumentException("User already has a database ID, updating is not allowed");
        }
        userRepository.save(user);
        return user;
    }

    /**
     * Update an existing User
     * @param user User
     * @return User
     */
    @Override
    public User updateUser(final User user)
    {
        log.info("Updating User {}", user);
        if (user.getId() == null)
        {
            throw new IllegalArgumentException("User already has a database ID, creating is not allowed");
        }
        return userRepository.save(user);
    }

    /**
     * Delete an existing User
     * @param userId Long
     */
    @Override
    public void deleteUser(final Long userId)
    {
        log.info("Deleting User with Id {}", userId);
        userRepository.deleteById(userId);
    }

    /**
     * Get a User by ID
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located User, or {@link Optional#empty()} if not found
     */
    @Override
    public Optional<User> getUser(final Long userId)
    {
        return userRepository.findById(userId);
    }

    /**
     * Get all Users older than a given date
     * @param date LocalDate
     * @return List of Users
     */
    @Override
    public List<User> getUsersOlderThan(LocalDate date)
    {
        return userRepository.findByBirthDateBefore(date);
    }

    /**
     * Get a User by email
     * @param email String
     * @return An {@link Optional} containing the located User, or {@link Optional#empty()} if not found
     */
    @Override
    public Optional<User> getUserByEmail(final String email)
    {
        return userRepository.findByEmail(email);
    }

    /**
     * Get a User by email ignoring case
     * @param email String
     * @return List of Users
     */
    @Override
    public List<User> getUserByEmailIgnoreCase(final String email)
    {
        return userRepository.findByEmailFragmentIgnoreCase(email);
    }

    /**
     * Get all Users
     * @return List of Users
     */
    @Override
    public List<User> findAllUsers()
    {
        return userRepository.findAll();
    }

}