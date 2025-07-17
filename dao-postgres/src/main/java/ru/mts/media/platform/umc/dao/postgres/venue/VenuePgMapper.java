package ru.mts.media.platform.umc.dao.postgres.venue;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.mts.media.platform.umc.dao.postgres.common.FullExternalIdPk;
import ru.mts.media.platform.umc.dao.postgres.event.EventPgEntity;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.FullExternalId;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import java.util.List;
import java.util.Set;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Named("VenuePgMapper")
@Mapper(componentModel = SPRING)
public interface VenuePgMapper {
    @Mapping(target = "externalId.brandId", source = "brand")
    @Mapping(target = "externalId.providerId", source = "provider")
    @Mapping(target = "externalId.externalId", source = "externalId")
    @Mapping(target = "id", source = "referenceId")
    @Mapping(target = "events", source = "events", qualifiedByName = "toDtoWithoutVenue")
    Venue asModel(VenuePgEntity venuePg);

    @Mapping(target = "referenceId", source = "id")
    @Mapping(target = "brand", source = "externalId.brandId")
    @Mapping(target = "provider", source = "externalId.providerId")
    @Mapping(target = "externalId", source = "externalId.externalId")
    VenuePgEntity asEntity(Venue venue);

    @Mapping(target = "brand", source = "brandId")
    @Mapping(target = "provider", source = "providerId")
    @Mapping(target = "externalId", source = "externalId")
    FullExternalIdPk asPk(FullExternalId fullExternalId);

    @Named("toDtoWithoutEvents")
    @Mapping(target = "externalId.brandId", source = "brand")
    @Mapping(target = "externalId.providerId", source = "provider")
    @Mapping(target = "externalId.externalId", source = "externalId")
    @Mapping(target = "id", source = "referenceId")
    @Mapping(target = "events", source = "events", ignore = true)
    List<Venue> toDtoWithoutEvents(Set<VenuePgEntity> venuePg);

    @Named("toEntitiesWithoutEvents")
    @Mapping(target = "externalId.brandId", source = "brand")
    @Mapping(target = "externalId.providerId", source = "provider")
    @Mapping(target = "externalId.externalId", source = "externalId")
    @Mapping(target = "id", source = "referenceId")
    @Mapping(target = "events", source = "events", ignore = true)
    Set<VenuePgEntity> toEntitiesWithoutEvents(List<Venue> venuePg);

    @Named("toDtoWithoutVenue")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "startTime", source = "startTime", resultType = String.class, dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(target = "endTime", source = "endTime", resultType = String.class, dateFormat = "dd-MM-yyyy HH:mm")
    @Mapping(target = "venue", source = "venue", ignore = true)
    List<Event> toDtoWithoutVenue(Set<EventPgEntity> eventPg);
}
