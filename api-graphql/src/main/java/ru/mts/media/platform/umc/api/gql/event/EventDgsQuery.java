package ru.mts.media.platform.umc.api.gql.event;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;
import ru.mts.media.platform.umc.domain.event.EventSot;
import ru.mts.media.platform.umc.domain.gql.types.Event;

import java.util.List;
import java.util.Optional;

@DgsComponent
@RequiredArgsConstructor
public class EventDgsQuery {
    private final EventSot sot;

    @DgsQuery
    public List<Event> eventsByVenueId(@InputArgument String venueId) {
        return Optional.of(venueId).flatMap(sot::getEventsByVanueId).orElse(null);
    }
}
