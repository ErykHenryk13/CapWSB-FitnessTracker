package pl.wsb.fitnesstracker.training.internal;

import io.micrometer.common.lang.Nullable;
import java.util.Date;

public record TrainingDtoWithUserId(
        @Nullable Long id,
        Long userId,
        Date startTime,
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed
)
{
}