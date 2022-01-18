DROP TABLE flight IF EXISTS;
DROP TABLE user IF EXISTS;

CREATE TABLE flight (
  id            BIGINT IDENTITY PRIMARY KEY,
  flight_num     BIGINT,
  name          VARCHAR(80),
  s_date        VARCHAR(80) ,
  a_date        VARCHAR(80) ,
  departure     VARCHAR(80),
  destination   VARCHAR(80),
  fare          DECIMAL(80,2),
  duration      DECIMAL(80,1),
);

CREATE TABLE user (
  id            BIGINT IDENTITY PRIMARY KEY,
  username      VARCHAR(80),
  password      VARCHAR(80)
);