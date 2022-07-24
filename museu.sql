/*Criando o banco de dados*/
create database Museu;


/*Criando todas as tabelas do banco de dados*/
create table Artista(
nome varchar(30) NOT NULL primary key,
dataNascimento date,
periodo varchar(30),
dataMorte date,
descricao varchar(40),
paisDeOrigem  varchar(20),
estiloPrincipal varchar(20)
);

create table Objeto_Arte(
numId int NOT NULL primary key,
ano int,
periodo varchar(30),
titulo varchar(30),
pais_CulturaDeOrigem varchar(20),
descricao varchar(40),
nome_Artista varchar(30),
custo double,
foreign key(nome_Artista) references Artista(nome) ON DELETE CASCADE
);

create table Exposicao(
nome varchar(30) NOT NULL primary key,
dataInicio date NOT NULL,
dataFim date NOT NULL,
numID_Objeto_Arte int,
foreign key(numID_Objeto_Arte) references Objeto_Arte(numId) ON DELETE CASCADE
);

create table Emprestados(
numID_Objeto_Arte int NOT NULL primary key,
dataDevolucao date,
dataEmprestimo date
);

create table Colecao(
nome varchar(30) NOT NULL primary key,
endereco varchar(30),
pessoaContato varchar(30),
descricao varchar(40),
tipo varchar(10),
numID_Objeto_Arte_Emprestados int,
foreign key(numID_Objeto_Arte_Emprestados) references Emprestados(numID_Objeto_Arte) ON DELETE CASCADE
);

create table Pintura(
numID_Objeto_Arte int not null primary key,
tipoTinta varchar(20),
suporte varchar(20),
estilo varchar(20),
foreign key(numID_Objeto_Arte) references Objeto_Arte(numId) ON DELETE CASCADE
);

create table Escultura(
numID_Objeto_Arte int NOT NULL primary key,
estilo varchar(20),
peso int,
altura double,
material varchar(50),
foreign key(numID_Objeto_Arte) references Objeto_Arte(numId) ON DELETE CASCADE
);

create table Outros(
numID_Objeto_Arte int NOT NULL primary key,
estilo varchar(20),
tipo varchar(20),
foreign key(numID_Objeto_Arte) references Objeto_Arte(numId) ON DELETE CASCADE
);

create table Permanentes(
numID_Objeto_Arte int NOT NULL primary key,
dataAquisicao date,
emExposicao enum('SIM','NÃO'),
foreign key(numID_Objeto_Arte) references Objeto_Arte(numId) ON DELETE CASCADE
);


select * from artista;
select * from pintura;
select * from escultura;
select * from outros;
select * from colecao;
select * from permanentes;
select * from objeto_arte, permanentes where numId = numID_Objeto_Arte;

select * from objeto_arte o inner join permanentes p on o.numId = p.numID_Objeto_Arte where p.dataAquisicao between '2021-3-00' and '2021-3-31';


select count(o.numId) from objeto_arte o inner join emprestados e2 on o.numId = e2.numID_Objeto_Arte
inner join pintura o2 on o2.numID_Objeto_Arte = e2.numID_Objeto_Arte where e2.dataEmprestimo between '2022-4-00' and '2022-4-30';

SET GLOBAL log_bin_trust_function_creators = 1;

DELIMITER $$
create function maiorNumeroColecaoEmprestimo(inicio date, fim date)
returns varchar(20)

BEGIN
declare escultura int;
declare pintura int;
declare outros int;
declare situacao varchar(30);

select count(o2.numID_Objeto_Arte) into pintura from objeto_arte o, emprestados e2, pintura o2 
where o.numId = e2.numID_Objeto_Arte and o2.numID_Objeto_Arte = e2.numID_Objeto_Arte and e2.dataEmprestimo between inicio and fim;
 
select count(o2.numID_Objeto_Arte) into escultura from objeto_arte o,emprestados e2, escultura o2 
where o.numId = e2.numID_Objeto_Arte and o2.numID_Objeto_Arte = e2.numID_Objeto_Arte and e2.dataEmprestimo between inicio and fim;

select count(o2.numID_Objeto_Arte) into outros from objeto_arte o,emprestados e2, outros o2 
where o.numId = e2.numID_Objeto_Arte and o2.numID_Objeto_Arte = e2.numID_Objeto_Arte and e2.dataEmprestimo between inicio and fim;


if escultura > pintura and escultura > outros THEN SET situacao = concat("Escultura: ",escultura);
elseif pintura > escultura and pintura > outros THEN SET situacao = concat("Pintura: ", pintura);
else SET situacao = concat("Outros: ",outros);
END IF;

return situacao;
END $$
DELIMITER ;

drop function maiorNumeroColecaoEmprestimo;
select maiorNumeroColecaoEmprestimo('2022-4-00','2022-4-30') as resultado;


select 'pintura', count(o.numId) from objeto_arte o inner join pintura p on p.numID_Objeto_Arte = o.numId
UNION
select 'outros', count(o.numId) from objeto_arte o inner join outros o2 on o2.numID_Objeto_Arte = o.numId
UNION
select 'escultura', count(o.numId) from objeto_arte o inner join escultura e on e.numID_Objeto_Arte = o.numId;

select * from objeto_arte o 
inner join permanentes p on o.numId = p.numID_Objeto_Arte
UNION
select * from objeto_arte o2 
inner join emprestados e on o2.numId = e.numID_Objeto_Arte 
order by dataAquisicao;



