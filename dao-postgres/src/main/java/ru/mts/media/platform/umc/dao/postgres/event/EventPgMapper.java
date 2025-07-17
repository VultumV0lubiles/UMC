package ru.mts.media.platform.umc.dao.postgres.event;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgMapper;
import ru.mts.media.platform.umc.domain.gql.types.Event;

import java.time.LocalDateTime;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Named("EventPgMapper")
@Mapper(componentModel = SPRING, uses = {VenuePgMapper.class})
public interface EventPgMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "startTime", source = "startTime", resultType = String.class, dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(target = "endTime", source = "endTime", resultType = String.class, dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(target = "venue", source = "venue", qualifiedByName = {"VenuePgMapper", "toDtoWithoutEvents"})
    Event asModel(EventPgEntity eventPg);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "startTime", source = "startTime", resultType = LocalDateTime.class, dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(target = "endTime", source = "endTime", resultType = LocalDateTime.class, dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(target = "venue", source = "venue", qualifiedByName = {"VenuePgMapper", "toEntitiesWithoutEvents"})
    EventPgEntity asEntity(Event event);
}