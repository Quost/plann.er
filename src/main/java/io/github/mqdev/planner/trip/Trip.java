package io.github.mqdev.planner.trip;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "trips")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String destination;

    @Column(name = "starts_at", nullable = false)
    private LocalDateTime startsAt;

    @Column(name = "ends_at", nullable = false)
    private LocalDateTime endsAt;

    @Column(name = "is_confirmed", nullable = false)
    private boolean isConfirmed;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "owner_email", nullable = false)
    private String ownerEmail;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Trip(TripRequestPayload payload) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        this.destination = payload.destination();
        this.isConfirmed = false;
        this.startsAt = LocalDateTime.parse(payload.starts_at(), formatter);
        this.endsAt = LocalDateTime.parse(payload.ends_at(), formatter);
        this.ownerName = payload.owner_name();
        this.ownerEmail = payload.owner_email();
        this.createdAt = LocalDateTime.now();
    }

    public void update(TripRequestPayload updatedTrip) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        Optional.ofNullable(updatedTrip.destination()).ifPresent(this::setDestination);
        Optional.ofNullable(updatedTrip.starts_at()).ifPresent(start -> this.setStartsAt(LocalDateTime.parse(start, formatter)));
        Optional.ofNullable(updatedTrip.ends_at()).ifPresent(end -> this.setEndsAt(LocalDateTime.parse(end, formatter)));
        Optional.ofNullable(updatedTrip.owner_name()).ifPresent(this::setOwnerName);
        Optional.ofNullable(updatedTrip.owner_email()).ifPresent(this::setOwnerEmail);
    }
}