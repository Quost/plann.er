package io.github.mqdev.planner.link;

import io.github.mqdev.planner.trip.Trip;
import org.springframework.stereotype.Service;

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
}
