package pl.wsb.fitnesstracker.training.api;

import jakarta.persistence.*;
import lombok.*;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;

@Entity
@Table(name = "trainings")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @Column(name = "start_time", nullable = false)
    public Date startTime;

    @Column(name = "end_time", nullable = false)
    public Date endTime;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "activity_type", nullable = false)
    public ActivityType activityType;

    @Column(name = "distance")
    public double distance;

    @Column(name = "average_speed")
    public double averageSpeed;

    public Training(
            final User user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}