CREATE TABLE image
(
    id        VARCHAR(255) NOT NULL,
    `path`    VARCHAR(255) NOT NULL,
    mime_type VARCHAR(255) NOT NULL,
    CONSTRAINT pk_image PRIMARY KEY (id)
);

ALTER TABLE image
    ADD CONSTRAINT uc_image_path UNIQUE (`path`);