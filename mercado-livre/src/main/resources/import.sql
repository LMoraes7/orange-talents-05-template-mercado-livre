-- SENHA 123456
insert into usuario(email, senha, instante_cadastro) values ('diego@email.com', '$2a$10$0Yks7PkM4mTwNdT.2ucnqOxNlURd0RQz.Pp.5l/XBERIGhwAZHB4a', utc_timestamp);
insert into usuario(email, senha, instante_cadastro) values ('carlos@email.com', '$2a$10$0Yks7PkM4mTwNdT.2ucnqOxNlURd0RQz.Pp.5l/XBERIGhwAZHB4a', utc_timestamp);

insert into categoria(nome) values ('Tecnologia');
insert into categoria(nome) values ('Eletrodomésticos');
insert into categoria(nome, categoria_mae_id) values ('Celulares', 1);

insert into produto(nome, valor, quant_estoque, descricao, categoria_id, usuario_id, instante_cadastro) values ('Samsung J5', 700.90, 50, 'Celular da Samsung', 3, 1, utc_timestamp);
insert into produto(nome, valor, quant_estoque, descricao, categoria_id, usuario_id, instante_cadastro) values ('Geladeira Arno', 1300.90, 20, 'Geladeira da Arno', 2, 1, utc_timestamp);
insert into produto(nome, valor, quant_estoque, descricao, categoria_id, usuario_id, instante_cadastro) values ('Notebook DELL', 2499.90, 10, 'Notebook da DELL', 1, 2, utc_timestamp);

insert into caracteristica(nome, descricao, produto_id) values ('textura','textura bonita', 1);
insert into caracteristica(nome, descricao, produto_id) values ('altura','altura bonita', 1);
insert into caracteristica(nome, descricao, produto_id) values ('largura','largura bonita', 1);

insert into caracteristica(nome, descricao, produto_id) values ('textura','textura bonita', 2);
insert into caracteristica(nome, descricao, produto_id) values ('altura','altura bonita', 2);
insert into caracteristica(nome, descricao, produto_id) values ('largura','largura bonita', 2);

insert into caracteristica(nome, descricao, produto_id) values ('textura','textura bonita', 3);
insert into caracteristica(nome, descricao, produto_id) values ('altura','altura bonita', 3);
insert into caracteristica(nome, descricao, produto_id) values ('largura','largura bonita', 3);