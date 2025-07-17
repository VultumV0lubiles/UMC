package ru.mts.media.platform.umc.domain.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.SaveEventInput;
import ru.mts.media.platform.umc.domain.venue.VenueSot;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventDomainService {
    private final ApplicationEventPublisher eventPublisher;
    private final VenueSot vanueSot;
    private final EventDomainServiceMapper mapper;

    public EventCreate create(String venueId, SaveEventInput input) {
        var venue = vanueSot.getVenueByReferenceId(venueId).orElse(null);
        if (venue != null) {
            var event = fillEvent(input);

            if (event.getVenue() != null) {
                event.getVenue().add(venue);
            } else {
                event.setVenue(List.of(venue));
            }

            var eventCreate = new EventCreate(event);
            eventPublisher.publishEvent(eventCreate);
            return eventCreate;
        }
        throw new IllegalArgumentException(String.format("Venue with referenceId %s not found", venueId));
    }

    private Event fillEvent(SaveEventInput updates) {
        return mapper.fromInput(updates);
    }
}
