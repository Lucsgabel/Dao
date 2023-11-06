create database BDCadastro;
use BDCadastro;

create table aluno (
  id int auto_increment primary key,
  nome varchar(255),
  email varchar(255),
  telefone varchar(255)
);

insert into aluno (nome, email, telefone) values ('Peter', 'peter@gmail.com', '555-555-5555');

select nome, email, telefone from aluno;
