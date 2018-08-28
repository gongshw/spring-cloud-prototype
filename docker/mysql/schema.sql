USE db;

DROP TABLE IF EXISTS model;
CREATE TABLE model (
  id          BIGINT             AUTO_INCREMENT PRIMARY KEY,
  content     TEXT,
  create_time TIMESTAMP NOT NULL,
  update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO model (content, create_time)
VALUES ('i am content', '2018-08-28 12:00:00');
