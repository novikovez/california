CREATE TABLE IF NOT EXISTS analysis
(
    id          BIGSERIAL PRIMARY KEY,
    external_id VARCHAR(255)     NOT NULL, /* внешний идентификатор */
    price       DOUBLE PRECISION NOT NULL, /* Цена на товар */
    purchase    DOUBLE PRECISION NOT NULL, /* Цена закупки */
    quantity    INT              NOT NULL, /* Остаток товара */
    created_at  TIMESTAMP DEFAULT NOW()
)