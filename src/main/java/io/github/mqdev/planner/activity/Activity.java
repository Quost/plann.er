package io.github.mqdev.planner.activity;

import io.github.mqdev.planner.trip.Trip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "activities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "occurs_at", nullable = false)
    private LocalDateTime occursAt;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Activity(String title, String occursAt, Trip trip) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        this.title = title;
        this.occursAt = LocalDateTime.parse(occursAt, formatter);
        this.trip = trip;
    }
}
