package io.github.mqdev.planner.participant;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService {

    public void registerParticipantsToTrip(List<String> emails, UUID tripId) {}

    public void triggerConfirmationEmailsToParticipants(UUID tripId) {}
}
