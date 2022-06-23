.headers on
.mode columns

DROP TABLE if EXISTS Person;

CREATE TABLE Person (
    idNumber INTEGER NOT NULL PRIMARY KEY,
    name TEXT NOT NULL,
    age INTEGER,
    image BLOB,

    CONSTRAINT check_age CHECK(age > 0)
);