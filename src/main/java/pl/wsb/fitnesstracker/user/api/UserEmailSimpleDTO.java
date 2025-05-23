package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * UserEmailSimpleDto is a simplified representation of the User entity with only id and email.
 */
public class UserEmailSimpleDTO {

    @Nullable
    private Long id;
    public String email;

    public UserEmailSimpleDTO(@Nullable Long id, String email)
    {
        this.id = id;
        this.email = email;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "UserEmailSimpleDto{id=" + id + ", email='" + email + "'}";
    }

    public String email() { return "UserEmailSimpleDto{id=" + id + ", email='" + email + "'}";
    }
}
