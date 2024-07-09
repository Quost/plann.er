package io.github.mqdev.planner.trip;

import io.github.mqdev.planner.participant.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trips")
public class TripController {

    private final ParticipantService participantService;

    private final TripRepository tripRepository;

    public TripController(ParticipantService participantService, TripRepository tripRepository) {
        this.participantService = participantService;
        this.tripRepository = tripRepository;
    }

    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody TripRequestPayload tripRequest) {
        try {
            Trip trip = new Trip(tripRequest);
            var savedTrip = tripRepository.save(trip);
            participantService.registerParticipantsToTrip(tripRequest.emails_to_invite(), savedTrip.getId());
            return ResponseEntity.ok(savedTrip);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}