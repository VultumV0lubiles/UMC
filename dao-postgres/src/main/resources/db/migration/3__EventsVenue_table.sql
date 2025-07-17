CREATE TABLE IF NOT EXISTS events_venue
(
    event_id    numeric(10) NOT NULL,
    venue_id    VARCHAR(255) NOT NULL
    PRIMARY KEY (event_id, venue_id)
    CONSTRAINT `fk_event_id` FOREIGN KEY (event_id) REFERENCES events(event_id),
    CONSTRAINT `fk_venue_id` FOREIGN KEY (venue_id) REFERENCES venue(reference_id));
);