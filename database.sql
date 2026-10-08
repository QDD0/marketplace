-- 2. Пользователи
CREATE TABLE client
(
    id_client   SERIAL PRIMARY KEY,
    first_name  VARCHAR(50)         NOT NULL,
    last_name   VARCHAR(50)         NOT NULL,
    surname     VARCHAR(60),
    email       VARCHAR(255) UNIQUE NOT NULL,
    password    VARCHAR(255)        NOT NULL,
    number      VARCHAR(20),
    date_create TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    role        VARCHAR(30),
    avatar      TEXT
);

-- 3. Бренды
CREATE TABLE brand
(
    id_brand   SERIAL PRIMARY KEY,
    name_brand VARCHAR(255) UNIQUE NOT NULL
);

-- 4. Категории товаров
CREATE TABLE category
(
    id_category          SERIAL PRIMARY KEY,
    name_category        VARCHAR(255) NOT NULL,
    description_category TEXT,
    parent_category_id   INTEGER
                                      REFERENCES category (id_category) ON DELETE SET NULL
);

-- 5. Товары
CREATE TABLE item
(
    id_item        SERIAL PRIMARY KEY,
    name_item      VARCHAR(255)   NOT NULL,
    id_brand       INTEGER
                                  REFERENCES brand (id_brand) ON DELETE SET NULL,
    id_category    INTEGER
                                  REFERENCES category (id_category) ON DELETE SET NULL,
    description    TEXT,
    characteristic JSONB          NOT NULL DEFAULT '{}'::jsonb,
    price          DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
    count_item     INTEGER        NOT NULL DEFAULT 0 CHECK (count_item >= 0),
    date_create    TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 6. Фотографии товаров
CREATE TABLE photo_item
(
    id_photo    SERIAL PRIMARY KEY,
    id_item     INTEGER NOT NULL
        REFERENCES item (id_item) ON DELETE CASCADE,
    photo_url   TEXT    NOT NULL,
    photo_order INTEGER NOT NULL DEFAULT 1 CHECK (photo_order > 0),
    UNIQUE (id_item, photo_order)
);

-- 7. Отзывы
CREATE TABLE review
(
    id_review          SERIAL PRIMARY KEY,
    id_client          INTEGER   NOT NULL
        REFERENCES client (id_client) ON DELETE CASCADE,
    id_item            INTEGER   NOT NULL
        REFERENCES item (id_item) ON DELETE CASCADE,
    description_review TEXT,
    photo              TEXT,
    rating             INTEGER   NOT NULL CHECK (rating BETWEEN 1 AND 5),
    date_review        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (id_client, id_item)
);

-- 8. Заказы
CREATE TABLE customer_order
(
    id_order    SERIAL PRIMARY KEY,
    id_client   INTEGER        NOT NULL
        REFERENCES client (id_client) ON DELETE RESTRICT,
    date_order  TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status      VARCHAR(30)    NOT NULL DEFAULT 'в ожидании'
        CHECK (status IN (
                          'в ожидании',
                          'подтвержденный',
                          'обработка',
                          'отправленный',
                          'доставленный',
                          'отмененный'
            )),
    total_price DECIMAL(10, 2) NOT NULL DEFAULT 0
        CHECK (total_price >= 0)
);

-- 9. Позиции заказов
CREATE TABLE order_item
(
    id_order_item SERIAL PRIMARY KEY,
    id_order      INTEGER        NOT NULL
        REFERENCES customer_order (id_order) ON DELETE CASCADE,
    id_item       INTEGER        NOT NULL
        REFERENCES item (id_item) ON DELETE RESTRICT,
    quantity      INTEGER        NOT NULL CHECK (quantity > 0),
    price         DECIMAL(10, 2) NOT NULL CHECK (price >= 0)
);

-- 10. Адреса доставки
CREATE TABLE delivery_address
(
    id_address    SERIAL PRIMARY KEY,
    id_client     INTEGER      NOT NULL
        REFERENCES client (id_client) ON DELETE CASCADE,
    delivery_type VARCHAR(50)  NOT NULL,
    city          VARCHAR(100) NOT NULL,
    street        VARCHAR(255) NOT NULL,
    house         VARCHAR(30)  NOT NULL,
    apartment     VARCHAR(30)
);
