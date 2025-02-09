CREATE TABLE IF NOT EXISTS competitors
(
    id         BIGSERIAL PRIMARY KEY,
    analysis_id BIGINT           NOT NULL, /* связь с таблицей analysis */
    price      DOUBLE PRECISION NOT NULL, /* Цена на товар */
    url        TEXT             NOT NULL, /* урл старницы */
    site       VARCHAR(255)     NOT NULL, /* сайт */
    relevant   BOOLEAN   DEFAULT FALSE,   /* релевантность */
    position   INT              NOT NULL, /* позиция */
    created_at TIMESTAMP DEFAULT NOW(),
    CONSTRAINT fk_orders_product FOREIGN KEY (analysis_id) REFERENCES analysis (id) ON DELETE CASCADE
)