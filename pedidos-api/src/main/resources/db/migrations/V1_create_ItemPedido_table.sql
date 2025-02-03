
CREATE TABLE item_pedido (
     id UUID PRIMARY KEY DEFAULT gen_random_uuid(),  -- Ou UUID() dependendo do seu banco
     pedido_id UUID NOT NULL,
     produto_id UUID NOT NULL,
     quantidade INTEGER,
     FOREIGN KEY (pedido_id) REFERENCES pedido(id),
     FOREIGN KEY (produto_id) REFERENCES produto(id)
);