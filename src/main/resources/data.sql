
INSERT INTO tb_clientes(id, nome, nascimento) VALUES(1, 'Paula Nunes', '12/12/1998');
INSERT INTO tb_clientes(id, nome, nascimento) VALUES(2, 'Maria Garcia', '05/03/1995');
INSERT INTO tb_clientes(id, nome, nascimento) VALUES(3, 'Carlos Martinez', '15/07/1985');
INSERT INTO tb_clientes(id, nome, nascimento) VALUES(4, 'Ana Rodriguez', '20/09/1990');
INSERT INTO tb_clientes(id, nome, nascimento) VALUES(5, 'Pedro Gomes', '30/12/1980');

INSERT INTO tb_enderecos(id, endereco, logradouro, cep, numero, cidade, endereco_principal, id_cliente)
VALUES (1 ,'Rua dos Pássaros', 'Praça da Liberdade', '30000-456', '120', 'Belo Horizonte', 'Sim', 1);
INSERT INTO tb_enderecos(id, endereco, logradouro, cep, numero, cidade, endereco_principal, id_cliente)
VALUES (2 ,'Avenida Paulista', 'Rua Augusta', '04567-890', '25', 'São Paulo', 'Sim', 2);
INSERT INTO tb_enderecos(id, endereco, logradouro, cep, numero, cidade, endereco_principal, id_cliente)
VALUES (3 ,'Rua das Flores', 'Avenida Beira-Mar', '12000-345', '50', 'Florianópolis', 'Sim', 3);INSERT INTO tb_enderecos(id, endereco, logradouro, cep, numero, cidade, endereco_principal, id_cliente)
VALUES (4 ,'Rua das Palmeiras', 'Rua do Ouvidor', '20000-123', '75', 'Salvador', 'Sim', 4);
INSERT INTO tb_enderecos(id, endereco, logradouro, cep, numero, cidade, endereco_principal, id_cliente)
VALUES (5 ,'Rua dos Pinheiros', 'Praça da Sé', '01000-000', '100', 'São Paulo', 'Sim', 5);
