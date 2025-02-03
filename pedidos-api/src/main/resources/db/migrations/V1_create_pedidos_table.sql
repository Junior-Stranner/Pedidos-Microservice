CREATE TABLE pedido (
   id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
   cliente VARCHAR(40),
   valor_total DOUBLE PRECISION,
   email_notificacao VARCHAR(120),
   status VARCHAR(40),
   data_hora TIMESTAMP WITHOUT TIME ZONE
);