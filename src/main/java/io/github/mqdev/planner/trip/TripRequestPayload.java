package io.github.mqdev.planner.trip;

import java.util.List;

public record TripRequestPayload(String destination, String startsAt, String endsAt, String ownerName, String ownerEmail, List<String> attendeesEmailsToInvite) {
}
