--########################################################################
--############# Usuários
--########################################################################
CREATE ROLE db2_role_admin;

-- # Role Admin
GRANT CREATE TABLE,
      CREATE SESSION,
      CREATE SEQUENCE,
      CREATE VIEW,
      CREATE PROCEDURE,
      CREATE TRIGGER
TO db2_role_admin;

-- # Role Atendimento
GRANT CREATE SESSION
TO db2_role_atendimento;

-- Usuario db2_admin
CREATE USER db2_admin
  IDENTIFIED BY db2_admin
  DEFAULT TABLESPACE "USERS"
  TEMPORARY TABLESPACE "TEMP";
  
ALTER USER db2_admin QUOTA 5M ON USERS;

GRANT db2_role_admin TO db2_admin with admin option;


--########################################################################
--############# Criando as Sequences
--########################################################################
DROP SEQUENCE seq_mesa;
CREATE SEQUENCE seq_mesa start with 1 increment by 1 nocycle;

DROP SEQUENCE seq_tipo_pagamento;
CREATE SEQUENCE seq_tipo_pagamento start with 1 increment by 1 nocycle;

DROP SEQUENCE seq_cliente;
CREATE SEQUENCE seq_cliente start with 1 increment by 1 nocycle;

DROP SEQUENCE seq_reserva;
CREATE SEQUENCE seq_reserva start with 1 increment by 1 nocycle;

DROP SEQUENCE seq_produto;
CREATE SEQUENCE seq_produto start with 1 increment by 1 nocycle;

DROP SEQUENCE seq_ingrediente;
CREATE SEQUENCE seq_ingrediente start with 1 increment by 1 nocycle;

DROP SEQUENCE seq_consumo;
CREATE SEQUENCE seq_consumo start with 1 increment by 1 nocycle;

DROP SEQUENCE seq_pagamento;
CREATE SEQUENCE seq_pagamento start with 1 increment by 1 nocycle;

DROP SEQUENCE seq_item_consumo;
CREATE SEQUENCE seq_item_consumo start with 1 increment by 1 nocycle;

DROP SEQUENCE seq_cardapio;
CREATE SEQUENCE seq_cardapio start with 1 increment by 1 nocycle;

--########################################################################
--############# Criando as tabelas
--########################################################################
DROP TABLE pagamento;
DROP TABLE tipo_pagamento;
DROP TABLE item_consumo;
DROP TABLE consumo;
DROP TABLE cardapio_produto;
DROP TABLE cardapio;
DROP TABLE detalhe_vinho;
DROP TABLE bebida;
DROP TABLE item_montavel;
DROP TABLE montavel;
DROP TABLE produto;
DROP TABLE ingrediente;
DROP TABLE funcionario;
DROP TABLE lista_reserva;
DROP TABLE mesa;
DROP TABLE cliente;

-- # Parametro
CREATE TABLE parametro(
  id_produto_couvert NUMBER(6,0)
);

-- # Mesa
CREATE TABLE mesa(
  numero_mesa NUMBER(6,0) NOT NULL,
  qtde_lugares NUMBER(6,0) CHECK ( qtde_lugares > 0 ),
  status NUMBER(1,0),
  CONSTRAINT pk_nr_mesa PRIMARY KEY(numero_mesa)
);
-- # Cliente
CREATE TABLE cliente(
  id_cliente NUMBER(6,0) NOT NULL,
  nome VARCHAR2(100),
  telefone NUMBER(11,0) CHECK ( length(telefone) >= 10 ),
  CONSTRAINT pk_id_cliente PRIMARY KEY(id_cliente)
);

-- # Lista de Reserva
CREATE TABLE lista_reserva(
  id_cliente NUMBER(6,0) NOT NULL,
  numero_mesa NUMBER(6,0) NOT NULL,
  id_reserva NUMBER(6,0) NOT NULL,
  data_hora TIMESTAMP,
  status NUMBER(1,0) default 0,
  CONSTRAINT pk_reserva_cliente PRIMARY KEY(id_cliente,numero_mesa,id_reserva),
  CONSTRAINT fk_reserva_cliente foreign key (id_cliente) references cliente(id_cliente) on delete cascade,
  CONSTRAINT fk_reserva_mesa foreign key (numero_mesa) references mesa(numero_mesa) on delete cascade
);

-- # funcionario
CREATE TABLE funcionario(
  cpf_funcionario NUMBER(11,0) NOT NULL,
  nome VARCHAR2(100),
  telefone NUMBER(11,0) CHECK ( length(telefone) >= 10 ),
  perfil NUMBER(1,0),
  login_usuario VARCHAR(15) NOT NULL UNIQUE,
  senha VARCHAR(60) NOT NULL,
  CONSTRAINT pk_cpf_funcionario PRIMARY KEY(cpf_funcionario)
);

-- # Produto - Cardapio
CREATE TABLE ingrediente(
  id_ingrediente NUMBER(6,0) NOT NULL,
  nome VARCHAR2(60),
  valor_custo NUMBER(6,2) CHECK ( valor_custo >= 0.0 ),
  qtde_estoque NUMBER(5,2) CHECK ( qtde_estoque >= 0.0 ),
  qtde_min_estoque NUMBER(5,2) CHECK ( qtde_min_estoque >= 0.0 ),
  flag_prato CHAR DEFAULT 'N' CHECK (flag_prato = 'Y' or flag_prato = 'N'),
  flag_bebida CHAR DEFAULT 'N' CHECK (flag_bebida = 'Y' or flag_bebida = 'N'),
  CONSTRAINT pk_ingrediente PRIMARY KEY(id_ingrediente)
);

CREATE TABLE produto (
  id_produto NUMBER(6,0) NOT NULL,
  nome VARCHAR2(60),
  percentual_lucro NUMBER(5,2) CHECK ( percentual_lucro >= 0 ),
  CONSTRAINT pk_produto PRIMARY KEY(id_produto)
);

CREATE TABLE montavel(
  id_produto NUMBER(6,0) NOT NULL,
  tipo NUMBER(1,0),
  CONSTRAINT pk_montavel_produto PRIMARY KEY(id_produto),
  CONSTRAINT fk_montavel_produto foreign key (id_produto) references produto(id_produto) on delete cascade
);

CREATE TABLE item_montavel(
  id_produto NUMBER(6,0) NOT NULL,
  id_ingrediente NUMBER(6,0),
  qtde NUMBER(5,2),
  CONSTRAINT pk_item_montavel PRIMARY KEY(id_produto,id_ingrediente),
  CONSTRAINT fk_item_montavel foreign key (id_produto) references produto(id_produto) on delete cascade,
  CONSTRAINT fk_item_ingrediente foreign key (id_ingrediente) references ingrediente(id_ingrediente)
);

CREATE TABLE bebida(
  id_produto NUMBER(6,0) NOT NULL,
  tipo NUMBER(1,0),
  valor_custo NUMBER(6,2) CHECK ( valor_custo >= 0 ),
  qtde_estoque NUMBER(3,0) CHECK(qtde_estoque >=0),
  qtde_min_estoque NUMBER(3,0) CHECK(qtde_min_estoque >=0),
  CONSTRAINT pk_bebida_produto PRIMARY KEY(id_produto),
  CONSTRAINT fk_bebida_produto foreign key (id_produto) references produto(id_produto) on delete cascade
);

CREATE TABLE detalhe_vinho(
  id_produto NUMBER(6,0) NOT NULL,
  safra NUMBER(4,0) CHECK(safra > 0),
  tipo_uva VARCHAR2(20) NOT NULL,
  CONSTRAINT pk_detalhe_vinho_produto PRIMARY KEY(id_produto),
  CONSTRAINT fk_detalhe_vinho_produto foreign key (id_produto) references produto(id_produto) on delete cascade
);

CREATE TABLE cardapio(
  id_cardapio NUMBER(6,0) NOT NULL,
  descricao VARCHAR2(40) NOT NULL,
  couvert NUMBER(6,0),
  ativo_hoje CHAR DEFAULT 'N' CHECK (ativo_hoje = 'Y' or ativo_hoje = 'N'),
  CONSTRAINT pk_cardapio PRIMARY KEY(id_cardapio)
);

CREATE TABLE cardapio_produto(
  id_cardapio NUMBER(6,0) NOT NULL,
  id_produto NUMBER(6,0) NOT NULL,
  CONSTRAINT pk_cardapio_produto PRIMARY KEY(id_cardapio,id_produto),
  CONSTRAINT fk_cardapio_produto_produto foreign key (id_produto) references produto(id_produto) on delete cascade
);

-- # Consumo mesas
CREATE TABLE consumo(
  id_consumo NUMBER(6,0) NOT NULL,
  cpf_funcionario NUMBER(11,0) NOT NULL,
  numero_mesa NUMBER(6,0) NOT NULL,
  qtde_pessoas NUMBER(2,0) NOT NULL,
  data_hora_inicio TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,
  data_hora_fim TIMESTAMP DEFAULT NULL,
  CONSTRAINT pk_consumo PRIMARY KEY(id_consumo),
  CONSTRAINT fk_consumo_funcionario foreign key (cpf_funcionario) references funcionario(cpf_funcionario),
  CONSTRAINT fk_consumo_mesa foreign key (numero_mesa) references mesa(numero_mesa) on delete set null
);

CREATE TABLE item_consumo(
  id_consumo NUMBER(6,0) NOT NULL,
  id_produto NUMBER(6,0) NOT NULL,
  id_item NUMBER(6,0) NOT NULL,
  qtde NUMBER(5,2) CHECK(qtde > 0),
  valor_venda NUMBER(6,2) CHECK(valor_venda > 0),
  CONSTRAINT pk_item_consumo PRIMARY KEY(id_consumo,id_produto,id_item),
  CONSTRAINT fk_item_consumo_produto foreign key (id_produto) references produto(id_produto),
  CONSTRAINT fk_item_consumo_consumo foreign key (id_consumo) references consumo(id_consumo)
);

-- # Pagamento
CREATE TABLE tipo_pagamento(
  id_tipo NUMBER(6,0),
  descricao VARCHAR2(20),
  CONSTRAINT pk_tipo_pagamento PRIMARY KEY(id_tipo)
);

CREATE TABLE pagamento(
  id_consumo NUMBER(6,0),
  id_pagamento NUMBER(6,0),
  id_tipo_pagamento NUMBER(6,0),
  valor NUMBER(12,2),
  CONSTRAINT pk_pagamento PRIMARY KEY(id_consumo,id_pagamento),
  CONSTRAINT fk_pagamento_consumo foreign key (id_consumo) references consumo(id_consumo) on delete cascade,
  CONSTRAINT fk_tipo_pagamento foreign key (id_tipo_pagamento) references tipo_pagamento(id_tipo) on delete cascade
);

--########################################################################
--############# Criando as Views
--########################################################################

--O software deve gerar relatórios diários, semanais e mensais contendo o faturamento do restaurante.
DROP VIEW v_rel_faturamento;
CREATE VIEW v_rel_faturamento AS
  select TO_DATE(c.data_hora_inicio, 'dd/MM/yyyy') as data,sum(qtde * valor_venda) as total
  from consumo c, item_consumo i
  where c.id_consumo = i.id_consumo
  group by TO_DATE(c.data_hora_inicio, 'dd/MM/yyyy'); 


--O software deve gerar um relatório contendo os itens e sua quantidade em estoque.
DROP VIEW v_rel_estoque_atual;
CREATE VIEW v_rel_estoque_atual AS
  select nome, 
         qtde_estoque,
         QTDE_MIN_ESTOQUE
  from ingrediente
  union all
  select p.nome, 
         b.qtde_estoque, 
         b.QTDE_MIN_ESTOQUE
  from produto p, 
       bebida  b
  where p.ID_PRODUTO = b.ID_PRODUTO
  order by qtde_estoque, nome;


--O software deve gerar um relatório contendo os pratos mais vendidos em um determinado período.
DROP VIEW v_rel_produto_mais_vendido;
CREATE VIEW v_rel_produto_mais_vendido AS
  select TO_DATE(c.data_hora_inicio, 'dd/MM/yyyy') as data,
         i.id_produto,
         p.nome,
         sum(i.qtde) as qtde
  from consumo      c, 
       item_consumo i,
       produto      p
  where c.id_consumo = i.id_consumo
        and i.id_produto = p.id_produto
  group by TO_DATE(c.data_hora_inicio, 'dd/MM/yyyy'),
           i.id_produto,
           p.nome;

--O software deve gerar um relatório contendo os itens consumidos por uma determinada mesa.
DROP VIEW v_rel_consumo_mesa;
CREATE VIEW v_rel_consumo_mesa AS
  select c.numero_mesa, 
         i.ID_PRODUTO,
         p.nome, 
         i.QTDE, 
         i.VALOR_VENDA
  from consumo      c, 
       item_consumo i,
       produto      p
  where c.id_consumo = i.id_consumo
        and i.id_produto = p.id_produto;

--O software deve gerar relatórios diários, semanais e mensais sobre o desempenho dos atendentes,
--  considerando quantidade de atendimentos e média de faturamento por atendente.
DROP VIEW v_rel_desempenho_atendente;
CREATE VIEW v_rel_desempenho_atendente AS
  select c.CPF_FUNCIONARIO,
         f.nome,
         TO_DATE(c.data_hora_inicio, 'dd/MM/yyyy') as data,
         count(1) total_atendimentos, 
         sum(i.QTDE * i.VALOR_VENDA) total_faturamento
  from consumo      c, 
       item_consumo i,
       funcionario  f
  where c.id_consumo = i.id_consumo
        and c.CPF_FUNCIONARIO = f.CPF_FUNCIONARIO
  group by c.CPF_FUNCIONARIO,
           f.nome,
           TO_DATE(c.data_hora_inicio, 'dd/MM/yyyy') ;


--########################################################################
--############# Populando
--########################################################################

--# Mesas
insert into mesa(NUMERO_MESA,QTDE_LUGARES,STATUS)
  values (seq_mesa.NEXTVAL, 4, 0);
insert into mesa(NUMERO_MESA,QTDE_LUGARES,STATUS)
  values (seq_mesa.NEXTVAL, 2, 0);
insert into mesa(NUMERO_MESA,QTDE_LUGARES,STATUS)
  values (seq_mesa.NEXTVAL, 6, 0);
insert into mesa(NUMERO_MESA,QTDE_LUGARES,STATUS)
  values (seq_mesa.NEXTVAL, 2, 0);
insert into mesa(NUMERO_MESA,QTDE_LUGARES,STATUS)
  values (seq_mesa.NEXTVAL, 4, 0);
--select * from mesa;

-- # Tipo de Pagamento
insert into tipo_pagamento(id_tipo, descricao)
  values(seq_tipo_pagamento.NEXTVAL,'Dinheiro');
insert into tipo_pagamento(id_tipo, descricao)
  values(seq_tipo_pagamento.NEXTVAL,'Cartão de Crédito');  
insert into tipo_pagamento(id_tipo, descricao)
  values(seq_tipo_pagamento.NEXTVAL,'Cartão de Débito');  
--select * from tipo_pagamento;

-- # Cliente
insert into cliente(id_cliente, nome,telefone)
  values(seq_cliente.NEXTVAL,'Cliente Teste 1',16992849673);  
insert into cliente(id_cliente, nome,telefone)
  values(seq_cliente.NEXTVAL,'Cliente Teste 2',16982789899);  
insert into cliente(id_cliente, nome,telefone)
  values(seq_cliente.NEXTVAL,'Cliente Teste 3',1691879898);  
insert into cliente(id_cliente, nome,telefone)
  values(seq_cliente.NEXTVAL,'Cliente Teste 4',1632339809);  
insert into cliente(id_cliente, nome,telefone)
  values(seq_cliente.NEXTVAL,'Cliente Teste 5',1633338989);  
--select * from cliente;
  
-- # Lista de Reserva
insert into lista_reserva(id_cliente, numero_mesa, id_reserva, data_hora, status)
  values(1,3,seq_reserva.NEXTVAL,systimestamp+2,0);
insert into lista_reserva(id_cliente, numero_mesa, id_reserva, data_hora, status)
  values(2,4,seq_reserva.NEXTVAL,systimestamp+1,0);
insert into lista_reserva(id_cliente, numero_mesa, id_reserva, data_hora, status)
  values(3,2,seq_reserva.NEXTVAL,systimestamp+1,0);
insert into lista_reserva(id_cliente, numero_mesa, id_reserva, data_hora, status)
  values(4,5,seq_reserva.NEXTVAL,systimestamp+1,0);
insert into lista_reserva(id_cliente, numero_mesa, id_reserva, data_hora, status)
  values(5,1,seq_reserva.NEXTVAL,systimestamp+1,0);
--select * from lista_reserva;

-- # Produto - Cardapio
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Tomate', 0.5, 20.0, 5.0, 'Y','N');
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Limão', 0.2, 20.0, 5.0, 'Y','Y');
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Uva', 0.2, 20.0, 5.0, 'N','Y');
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Queijo Prato', 0.2, 20.0, 5.0, 'Y','N');
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Queijo Gorgonzola', 0.2, 20.0, 5.0, 'Y','N');
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Picanha', 15.0, 10.0, 2.0, 'Y','N');  
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Batata Palito', 8.0, 40.0, 2.0, 'Y','N');
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Batata', 0.3, 15.0, 2.0, 'Y','N');
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Calabresa', 2.0, 5.0, 2.0, 'Y','N');
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Carne seca', 5.5, 15.0, 5.0, 'Y','N');
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Queijo coalho', 5.5, 5.0, 2.0, 'Y','N');
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Filé de tilápia', 10.5, 5.0, 2.0, 'Y','N');
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Maracujá', 0.3, 15.0, 2.0, 'Y','Y');
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Cenoura', 0.3, 15.0, 2.0, 'Y','N');
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Ervilha', 0.3, 15.0, 2.0, 'Y','N');
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Milho', 0.2, 15.0, 2.0, 'Y','N');
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Abacaxi', 0.3, 5.0, 2.0, 'N','Y');
-- select * from ingrediente;
select seq_ingrediente.NEXTVAL from dual;
--Doses de bebidas
insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Dose Martine', 3, 5.0, 0.5, 'N','Y');

insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida)
  values(seq_ingrediente.NEXTVAL, 'Ingredientes básicos', 3, 5.0, 0.5, 'N','Y');

--# Prato X
INSERT ALL
INTO produto(id_produto, nome,percentual_lucro) values (seq_produto.NEXTVAL,nome,percentual_lucro)
INTO montavel(id_produto,tipo) values (seq_produto.CURRVAL, tipo)
SELECT 'Filé tilápia' as nome,80.0 as percentual_lucro, 0 as tipo FROM dual;
-- >> itens
INSERT ALL
INTO item_montavel(id_produto,id_ingrediente,qtde) values (seq_produto.CURRVAL,id_ingrediente,qtde)
  (
    select 12 as id_ingrediente, 1.0 as qtde from dual union all
    select 2, 0.5 from dual union all
    select 1, 1.0 from dual
  );
--select * from produto p,montavel m, item_montavel i where i.id_produto = p.id_produto and m.id_produto = p.id_produto;
