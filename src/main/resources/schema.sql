CREATE TABLE urls
(
    url_id     IDENTITY        PRIMARY KEY,
    original_url   VARCHAR(255)    NOT NULL,
    req_cnt    INT NOT NULL
);