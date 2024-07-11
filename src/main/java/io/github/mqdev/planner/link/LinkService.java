package io.github.mqdev.planner.link;

import io.github.mqdev.planner.trip.Trip;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public LinkCreateResponse createLink(LinkRequestPayload linkRequestPayload, Trip trip) {
        Link link = new Link(linkRequestPayload.title(), linkRequestPayload.url(), trip);
        linkRepository.save(link);
        return new LinkCreateResponse(link.getId());
    }

    public List<LinkData> getAllTripLinks(UUID tripId) {
        return linkRepository.findByTripId(tripId).stream()
                .map(link -> new LinkData(link.getId(), link.getTitle(), link.getUrl()))
                .toList();
    }
}
