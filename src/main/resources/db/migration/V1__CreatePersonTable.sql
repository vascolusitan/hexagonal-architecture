CREATE TABLE person (
    person_id VARCHAR(255) NOT NULL,
    message_id VARCHAR(255),
    timestamp DATETIME NOT NULL,
    name VARCHAR(255) NOT NULL,
    sex VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    maturity VARCHAR(255) NOT NULL,
    PRIMARY KEY (person_id)
);