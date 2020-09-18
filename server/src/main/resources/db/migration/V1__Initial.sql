
CREATE TABLE PERSON (
    ID BIGSERIAL NOT NULL,
    FULL_NAME VARCHAR(100) NOT NULL,
    DOCUMENT VARCHAR(20) NOT NULL,
    PHONE_NUMBER VARCHAR(20) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE CHECKIN (
    ID BIGSERIAL NOT NULL,
    ENTRY_TIME TIMESTAMP NOT NULL,
    DEPARTURE_TIME TIMESTAMP,
    PERSON_ID BIGINT NOT NULL,
    HAS_VEHICLE BOOLEAN DEFAULT FALSE NOT NULL,
    FINAL_VALUE DECIMAL(15, 2),
    FOREIGN KEY(PERSON_ID) REFERENCES PERSON(ID),
    PRIMARY KEY (ID)
);
