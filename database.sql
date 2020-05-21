-- we don't know how to generate root <with-no-name> (class Root) :(
create table TBL_CLIENTE
(
	cli_codigo int auto_increment
		primary key,
	cli_nome varchar(45) null,
	cli_cpf varchar(14) null,
	cli_ultima_compra datetime null
);

create table TBL_PRODUTO
(
	prod_codigo int auto_increment
		primary key,
	prod_descricao varchar(45) null,
	prod_saldo decimal(12,2) null,
	prod_unidade varchar(15) null
);

create table TBL_PRODUTO_MOVIMENTACAO
(
	prodm_codigo int auto_increment
		primary key,
	prodm_data datetime null,
	prodm_descricao text null,
	prodm_cod_PRODUTO int null,
	constraint FK_Prod
		foreign key (prodm_cod_PRODUTO) references TBL_PRODUTO (prod_codigo)
);

create table TBL_VENDEDOR
(
	vend_codigo int auto_increment
		primary key,
	vend_nome varchar(45) null,
	vend_percentual_comissao decimal(3,2) null
);

create table TBL_PEDIDO
(
	ped_codigo int auto_increment
		primary key,
	ped_data datetime null,
	ped_observacao varchar(255) null,
	ped_cod_CLIENTE int null,
	ped_cod_VENDEDOR int null,
	constraint FK_Cliente
		foreign key (ped_cod_CLIENTE) references TBL_CLIENTE (cli_codigo),
	constraint FK_Vendedor
		foreign key (ped_cod_VENDEDOR) references TBL_VENDEDOR (vend_codigo)
);

create table TBL_PEDIDO_PRODUTO
(
	pedp_codigo int auto_increment
		primary key,
	pedp_quantidade int unsigned null,
	pedp_valor decimal(18,2) null,
	pedp_valor_total decimal(18,3) null,
	pedp_cod_PRODUTO int null,
	pedp_cod_PEDIDO int null,
	constraint FK_Pedido
		foreign key (pedp_cod_PEDIDO) references TBL_PEDIDO (ped_codigo),
	constraint FK_Produto
		foreign key (pedp_cod_PRODUTO) references TBL_PRODUTO (prod_codigo)
);

create table TBL_VENDEDOR_COMISSAO
(
	vnc_codigo int auto_increment
		primary key,
	vnc_comissao decimal(12,2) null,
	vnc_cod_VENDEDOR int null,
	vnc_cod_PEDIDO int null,
	constraint FK_Ped
		foreign key (vnc_cod_PEDIDO) references TBL_PEDIDO (ped_codigo),
	constraint FK_Vend
		foreign key (vnc_cod_VENDEDOR) references TBL_VENDEDOR (vend_codigo)
);

// ============================================================================================================================

INSERT INTO db_java.TBL_CLIENTE (cli_codigo, cli_nome, cli_cpf, cli_ultima_compra) VALUES (1, 'Marcos Oliveira', '123.123.123-33', '2020-04-12 14:10:26');
INSERT INTO db_java.TBL_CLIENTE (cli_codigo, cli_nome, cli_cpf, cli_ultima_compra) VALUES (2, 'Saulo Campos Cunha', '123.123.123', '2020-04-11 16:43:51');

INSERT INTO db_java.TBL_PRODUTO (prod_codigo, prod_descricao, prod_saldo, prod_unidade) VALUES (1, 'HeadSet Corsair HS50', 25.00, 'uni');
INSERT INTO db_java.TBL_PRODUTO (prod_codigo, prod_descricao, prod_saldo, prod_unidade) VALUES (2, 'Mouse Logitech HS403', 46.00, 'unidade');
INSERT INTO db_java.TBL_PRODUTO (prod_codigo, prod_descricao, prod_saldo, prod_unidade) VALUES (3, 'Monitor LG Ultrawid', 50.00, 'unidade');

INSERT INTO db_java.TBL_VENDEDOR (vend_codigo, vend_nome, vend_percentual_comissao) VALUES (1, 'Rafael Oliveira', 1.00);
INSERT INTO db_java.TBL_VENDEDOR (vend_codigo, vend_nome, vend_percentual_comissao) VALUES (2, 'Antônio Reis', 4.00);
