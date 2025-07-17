package ru.mts.media.platform.umc.dao.postgres.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.mts.media.platform.umc.domain.event.EventCreate;
import ru.mts.media.platform.umc.domain.event.EventSot;
import ru.mts.media.platform.umc.domain.gql.types.Event;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class EventPgDao implements EventSot {
    private final EventPgRepository repository;
    private final EventPgMapper mapper;

    @EventListener
    public void handleEventCreatedEvent(EventCreate evt) {
        evt.unwrap()
                .map(mapper::asEntity)
                .ifPresent(repository::save);
    }

    @Override
    public Optional<List<Event>> getEventsByVanueId(String id) {
        return Optional.of(id)
                .map(repository::findAllByVenueReferenceId)
                .map(entities -> entities.stream()
                        .map(mapper::asModel)
                        .collect(Collectors.toList())
                );
    }
}
