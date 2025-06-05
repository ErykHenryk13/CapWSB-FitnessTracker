package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.internal.UserMapper;

@Component
@RequiredArgsConstructor
public class TrainingMapper {

    private final UserMapper userMapper;
    private final UserProvider userProvider;

    /**
     * Converts a Training entity to a Training DTO.
     *
     * @param training the Training entity
     * @return the Training DTO
     */
    public TrainingDto toDto(Training training) {
        if (training == null) {
            throw new IllegalArgumentException("Training entity cannot be null");
        }

        return new TrainingDto(
                training.getId(),
                userMapper.toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    /**
     * Converts a Training DTO to a Training entity.
     *
     * @param trainingDto the Training DTO
     * @return the Training entity
     * @throws IllegalArgumentException if the user with the provided ID is not found
     */
    public Training toEntity(TrainingDtoWithUserId trainingDto) {
        if (trainingDto == null) {
            throw new IllegalArgumentException("Training DTO cannot be null");
        }

        User user = userProvider.getUser(trainingDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("User with Id " + trainingDto.userId() + " was not found"));

        return new Training(
                user,
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed()
        );
    }
}
