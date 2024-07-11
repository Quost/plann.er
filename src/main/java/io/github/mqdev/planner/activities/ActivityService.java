package io.github.mqdev.planner.activities;

import io.github.mqdev.planner.trip.Trip;
import org.springframework.stereotype.Service;

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
}
