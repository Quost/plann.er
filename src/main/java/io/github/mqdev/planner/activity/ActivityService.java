package io.github.mqdev.planner.activity;

import io.github.mqdev.planner.trip.Trip;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public ActivityCreateResponse createActivity(ActivityRequestPayload payload, Trip trip) {
        Activity activity = new Activity(payload.title(), payload.occurs_at(), trip);

        activityRepository.save(activity);

        return new ActivityCreateResponse(activity.getId());
    }

    public List<ActivityData> getAllTripActivities(UUID tripId) {
        return activityRepository.findByTripId(tripId).stream()
                .map(activity -> new ActivityData(activity.getId(), activity.getTitle(), activity.getOccursAt()))
                .toList();
    }
}
