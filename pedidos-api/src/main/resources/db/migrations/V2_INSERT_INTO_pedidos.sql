INSERT INTO pedido (id, cliente, valor_total, email_notificacao, status, data_hora)
VALUES (gen_random_uuid(), 'Bubu', 65.00, 'hstrannerjr.dev@gmail.com', 'EM_PROCESSAMENTO', NOW());