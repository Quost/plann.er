package io.github.mqdev.planner.participant;

import io.github.mqdev.planner.trip.Trip;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public void registerParticipantsToTrip(List<String> emails, Trip trip) {
        participantRepository.saveAll(
                emails.stream().map(email -> new Participant(email, trip)).toList()
        );
    }

    public ParticipantCreateResponse registerParticipantToTrip(String email, Trip trip) {
        Participant participant = participantRepository.save(new Participant(email, trip));
        return new ParticipantCreateResponse(participant.getId());
    }

    public void triggerConfirmationEmailsToParticipants(UUID tripId) {}

    public void triggerConfirmationEmailToParticipant(String email) {
    }
}
