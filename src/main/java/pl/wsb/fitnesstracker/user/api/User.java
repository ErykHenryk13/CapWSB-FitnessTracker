package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, unique = true)
    private String email;

    public User(String firstName, String lastName, LocalDate birthdate, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }
}
