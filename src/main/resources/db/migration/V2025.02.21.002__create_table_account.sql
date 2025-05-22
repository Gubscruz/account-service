CREATE TABLE account (
    id_account VARCHAR(36) NOT NULL,
    tx_name VARCHAR(256) NOT NULL,
    tx_email VARCHAR(256) NOT NULL,
    tx_sha256 VARCHAR(64) NOT NULL,
    dt_birthdate DATE NULL,
    dt_creation TIMESTAMP NOT NULL,
    CONSTRAINT pk_account PRIMARY KEY (id_account)
);
