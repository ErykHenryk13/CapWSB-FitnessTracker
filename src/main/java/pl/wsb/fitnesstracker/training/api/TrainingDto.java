package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.UserDto;
import jakarta.annotation.Nullable;
import java.util.Date;

/**
 * Data transfer object (DTO) representing a training session.
 * This DTO is used to transfer training data to/from the system.
 */
public record TrainingDto(
        @Nullable Long id,
        UserDto user,
        Date startTime,
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed
) {
    // Optional: Additional methods or constructors could be added here if needed.
}
