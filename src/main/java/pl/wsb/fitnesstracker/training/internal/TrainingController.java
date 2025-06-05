package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Controller class for handling training-related HTTP requests.
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
@Slf4j
public class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    // Helper method to map training entities to DTOs
    private List<TrainingDto> mapTrainingsToDto(List<Training> trainings) {
        return trainings.stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all trainings.
     *
     * @return List of all training DTOs
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        log.info("Fetching all trainings");
        List<Training> trainings = trainingService.findAllTrainings();
        return mapTrainingsToDto(trainings);
    }

    /**
     * Retrieves all trainings by a specific user.
     *
     * @param userId ID of the user
     * @return List of training DTOs for the given user
     */
    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsByUser(@PathVariable Long userId) {
        log.info("Fetching trainings for user with ID: {}", userId);
        List<Training> trainings = trainingService.findTrainingByUser(userId);
        return mapTrainingsToDto(trainings);
    }

    /**
     * Retrieves all finished trainings after a certain time.
     *
     * @param afterTime the time after which trainings must have finished
     * @return List of finished training DTOs
     */
    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getFinishedTrainings(
            @PathVariable("afterTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime) {
        log.info("Fetching finished trainings after: {}", afterTime);
        List<Training> trainings = trainingService.findFinishedTrainings(afterTime);
        return mapTrainingsToDto(trainings);
    }

    /**
     * Retrieves all trainings of a specific activity type.
     *
     * @param activityType the type of activity to filter trainings by
     * @return List of training DTOs for the given activity type
     */
    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivityType(
            @RequestParam("activityType") ActivityType activityType) {
        log.info("Fetching trainings for activity type: {}", activityType);
        List<Training> trainings = trainingService.findTrainingsByActivityType(activityType);
        return mapTrainingsToDto(trainings);
    }

    /**
     * Creates a new training record.
     *
     * @param trainingDto the DTO representation of the training to create
     * @return the created training DTO
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody TrainingDtoWithUserId trainingDto) {
        log.info("Creating a new training: {}", trainingDto);
        Training training = trainingMapper.toEntity(trainingDto);
        Training savedTraining = trainingService.createTraining(training);
        return trainingMapper.toDto(savedTraining);
    }

    /**
     * Updates an existing training record.
     *
     * @param id          the ID of the training to update
     * @param trainingDto the DTO representation of the updated training
     * @return the updated training DTO
     */
    @PutMapping("/{id}")
    public TrainingDto updateTraining(@PathVariable Long id, @RequestBody TrainingDtoWithUserId trainingDto) {
        log.info("Updating training with ID: {}", id);
        Training updatedTraining = trainingService.updateTraining(id, trainingMapper.toEntity(trainingDto));
        return trainingMapper.toDto(updatedTraining);
    }

    /**
     * Partially updates an existing training record.
     *
     * @param id      the ID of the training to update
     * @param updates a map containing the fields to update and their new values
     * @return the updated training DTO
     */
    @PatchMapping("/{id}")
    public TrainingDto partiallyUpdateTraining(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        log.info("Partially updating training with ID: {}", id);
        Training updatedTraining = trainingService.partiallyUpdateTraining(id, updates);
        return trainingMapper.toDto(updatedTraining);
    }

    /**
     * Deletes a training record.
     *
     * @param id the ID of the training to delete
     * @return an empty response indicating the training was deleted
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteTraining(@PathVariable Long id) {
        log.info("Deleting training with ID: {}", id);
        trainingService.deleteTraining(id);
        return ResponseEntity.noContent().build();
    }
}
