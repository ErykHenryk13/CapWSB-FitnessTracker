package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for retrieving operations on {@link Training} entities.
 * Implementing classes are responsible for fetching trainings from the database.
 */
public interface TrainingProvider {

    /**
     * Retrieves a training based on its ID.
     * If the training with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<Training> getTraining(Long trainingId);  // Should return Optional<Training>, not Optional<User>

    /**
     * Retrieves all trainings.
     *
     * @return A list containing all trainings
     */
    List<Training> findAllTrainings();

    /**
     * Retrieves all trainings for a given user.
     *
     * @param userId id of the user
     * @return A list of all trainings for the specified user
     */
    List<Training> findTrainingByUser(Long userId);

    /**
     * Retrieves all trainings for a given user.
     *
     * @param user the user for which trainings should be retrieved
     * @return A list of trainings for the specified user
     */
    List<Training> findByUserObject(User user);

    /**
     * Retrieves all trainings that have finished after a given time.
     *
     * @param endTime time after which the training has finished
     * @return A list of all trainings that have finished after the specified time
     */
    List<Training> findFinishedTrainings(Date endTime);

    /**
     * Retrieves all trainings of a specific activity type.
     *
     * @param activityType the type of activity for which trainings should be retrieved
     * @return A list of trainings of the specified activity type
     */
    List<Training> findTrainingsByActivityType(ActivityType activityType);
}
