CREATE TABLE produto (
   id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
   nome VARCHAR(255),
   valor DOUBLE PRECISION
);