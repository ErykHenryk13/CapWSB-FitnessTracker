package pl.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

/**
 * UserSimpleDto is a simple representation of User entity.
 */
public record UserSimpleDto(@Nullable Long id, String firstName, String lastName)
{
}