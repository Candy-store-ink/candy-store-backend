CREATE TABLE IF NOT EXISTS category
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    name  VARCHAR(255)          NULL,
    image VARCHAR(255)          NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS favorite_products
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    user_id    BIGINT                NULL,
    product_id BIGINT                NULL,
    CONSTRAINT pk_favorite_products PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS manager
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    name              VARCHAR(255)          NULL,
    second_name       VARCHAR(255)          NULL,
    email             VARCHAR(255)          NOT NULL,
    phone             VARCHAR(255)          NULL,
    price_coefficient DECIMAL(10, 2)        NULL,
    CONSTRAINT pk_manager PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS order_products
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    quantity   INT                   NULL,
    price      DECIMAL(10, 2)        NULL,
    product_id BIGINT                NULL,
    order_id   BIGINT                NULL,
    CONSTRAINT pk_order_products PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    name            VARCHAR(255)          NULL,
    `description`   TEXT                  NULL,
    image           VARCHAR(255)          NULL,
    price           DECIMAL(10, 2)        NULL,
    manufacturer    VARCHAR(255)          NULL,
    expiration_date datetime              NULL,
    is_visible      BIT(1)                NULL,
    is_removed      BIT(1)                NULL,
    category_id     BIGINT                NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    name        VARCHAR(255)          NULL,
    second_name VARCHAR(255)          NULL,
    email       VARCHAR(255)          NOT NULL,
    password    VARCHAR(255)          NOT NULL,
    phone       VARCHAR(255)          NULL,
    `role`      VARCHAR(255)          NOT NULL,
    manager_id  BIGINT                NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_order
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    total_amount DECIMAL(10, 2)        NULL,
    created_at   datetime              NULL,
    user_id      BIGINT                NULL,
    CONSTRAINT pk_user_order PRIMARY KEY (id)
);

ALTER TABLE category
    ADD CONSTRAINT UK_ON_CATEGORY_NAME UNIQUE (name);

ALTER TABLE manager
    ADD CONSTRAINT UK_ON_MANAGER_EMAIL UNIQUE (email);

ALTER TABLE user
    ADD CONSTRAINT UK_ON_USER_EMAIL UNIQUE (email);

ALTER TABLE favorite_products
    ADD CONSTRAINT FK_FAVORITE_PRODUCTS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE favorite_products
    ADD CONSTRAINT FK_FAVORITE_PRODUCTS_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE order_products
    ADD CONSTRAINT FK_ORDER_PRODUCTS_ON_ORDER FOREIGN KEY (order_id) REFERENCES user_order (id);

ALTER TABLE order_products
    ADD CONSTRAINT FK_ORDER_PRODUCTS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_MANAGER FOREIGN KEY (manager_id) REFERENCES manager (id);

ALTER TABLE user_order
    ADD CONSTRAINT FK_USER_ORDER_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);