package ru.mts.media.platform.umc.domain.event;

import ru.mts.media.platform.umc.domain.common.EntityEvent;
import ru.mts.media.platform.umc.domain.gql.types.Event;

public class EventCreate extends EntityEvent<Event> {
    public EventCreate(Event entity) {
        super(entity);
    }
}
