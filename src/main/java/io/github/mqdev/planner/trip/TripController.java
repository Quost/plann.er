package io.github.mqdev.planner.trip;

import io.github.mqdev.planner.activities.ActivityCreateResponse;
import io.github.mqdev.planner.activities.ActivityData;
import io.github.mqdev.planner.activities.ActivityRequestPayload;
import io.github.mqdev.planner.activities.ActivityService;
import io.github.mqdev.planner.participant.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
public class TripController {

    private final ParticipantService participantService;

    private final TripRepository tripRepository;

    private final ActivityService activityService;

    public TripController(ParticipantService participantService, TripRepository tripRepository, ActivityService activityService) {
        this.participantService = participantService;
        this.tripRepository = tripRepository;
        this.activityService = activityService;
    }

    @PostMapping
    public ResponseEntity<TripCreateResponse> createTrip(@RequestBody TripRequestPayload tripRequest) {
        try {
            Trip trip = new Trip(tripRequest);
            var savedTrip = tripRepository.save(trip);
            participantService.registerParticipantsToTrip(tripRequest.emails_to_invite(), savedTrip);
            return ResponseEntity.ok(new TripCreateResponse(savedTrip.getId(), savedTrip.getCreatedAt()));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<Trip> getTripDetails(@PathVariable UUID tripId) {
        Optional<Trip> trip = tripRepository.findById(tripId);

        return trip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{tripId}")
    public ResponseEntity<Trip> updateTrip(@PathVariable UUID tripId, @RequestBody TripRequestPayload payload) {
        Optional<Trip> trip = tripRepository.findById(tripId);

        if (trip.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Trip updatedTrip = trip.get();
        updatedTrip.update(payload);

        return ResponseEntity.ok(this.tripRepository.save(updatedTrip));
    }

    @GetMapping("/{tripId}/confirm")
    public ResponseEntity<Trip> confirmTrip(@PathVariable UUID tripId) {
        Optional<Trip> trip = tripRepository.findById(tripId);

        if (trip.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Trip confirmedTrip = trip.get();
        confirmedTrip.setConfirmed(true);
        this.participantService.triggerConfirmationEmailsToParticipants(tripId);

        return ResponseEntity.ok(this.tripRepository.save(confirmedTrip));
    }

    @PostMapping("/{tripId}/invite")
    public ResponseEntity<ParticipantCreateResponse> inviteParticipant(@PathVariable UUID tripId, @RequestBody ParticipantRequestPayload payload) {
        Optional<Trip> trip = tripRepository.findById(tripId);

        if (trip.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Trip updatedTrip = trip.get();
        String email = payload.email();
        ParticipantCreateResponse participantResponse = participantService.registerParticipantToTrip(email, updatedTrip);

        if(updatedTrip.isConfirmed()) {
            this.participantService.triggerConfirmationEmailToParticipant(email);
        }

        return ResponseEntity.ok(participantResponse);
    }

    @GetMapping("/{tripId}/participants")
    public ResponseEntity<List<ParticipantData>> getParticipants(@PathVariable UUID tripId) {
        List<ParticipantData> participantList = participantService.getAllTripParticipants(tripId);

        return ResponseEntity.ok(participantList);
    }

    @PostMapping("/{tripId}/activities")
    public ResponseEntity<ActivityCreateResponse> createActivity(@PathVariable UUID tripId, @RequestBody ActivityRequestPayload payload) {
        Optional<Trip> trip = tripRepository.findById(tripId);

        if (trip.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Trip updatedTrip = trip.get();
        ActivityCreateResponse activityResponse = activityService.createActivity(payload, updatedTrip);

        return ResponseEntity.ok(activityResponse);
    }

    @GetMapping("/{tripId}/activities")
    public ResponseEntity<List<ActivityData>> getActivities(@PathVariable UUID tripId) {
        List<ActivityData> activityList = activityService.getAllTripActivities(tripId);

        return ResponseEntity.ok(activityList);
    }

}