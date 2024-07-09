package io.github.mqdev.planner.trip;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TripController {

    @PostMapping("/trips")
    public ResponseEntity<Trip> createTrip(@RequestBody TripRequestPayload tripRequest) {
        try {
            Trip trip = new Trip(tripRequest);
            return ResponseEntity.ok(trip);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}