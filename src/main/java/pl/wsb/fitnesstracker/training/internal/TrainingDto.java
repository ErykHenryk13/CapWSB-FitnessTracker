package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.user.api.UserDto;
import io.micrometer.common.lang.Nullable;
import java.util.Date;

/**
 * Data transfer object (DTO) representing a training.
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
    public TrainingDto(Long id, pl.wsb.fitnesstracker.user.internal.UserDto dto, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
        this(id, userFromInternal(dto), startTime, endTime, activityType, distance, averageSpeed);
    }

    private static UserDto userFromInternal(pl.wsb.fitnesstracker.user.internal.UserDto internalDto) {
        return null;
    }
}