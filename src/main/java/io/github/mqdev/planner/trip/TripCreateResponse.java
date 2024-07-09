package io.github.mqdev.planner.trip;

import java.time.LocalDateTime;
import java.util.UUID;

public record TripCreateResponse(UUID tripId, LocalDateTime createdAt) {
}
