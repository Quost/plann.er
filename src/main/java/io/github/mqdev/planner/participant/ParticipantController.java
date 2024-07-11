package io.github.mqdev.planner.participant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    private final ParticipantRepository participantRepository;

    public ParticipantController(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }


    @PostMapping("/{participantId}/confirm")
    public ResponseEntity<Participant> confirmParticipant(@PathVariable UUID participantId, @RequestBody ParticipantRequestPayload payload) {
        Optional<Participant> participant = participantRepository.findById(participantId);

        if (participant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Participant updatedParticipant = participant.get();
        updatedParticipant.setIsConfirmed(true);
        updatedParticipant.setName(payload.name());

        return ResponseEntity.ok(this.participantRepository.save(updatedParticipant));
    }
}
