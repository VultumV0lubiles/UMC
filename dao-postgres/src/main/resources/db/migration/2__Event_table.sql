CREATE TABLE IF NOT EXISTS events
(
    id          numeric(10) NOT NULL,
    name        VARCHAR(255) NOT NULL,
    startTime   timestamp NOT NULL,
    endTime     timestamp NOT NULL,
    PRIMARY KEY (id)
);