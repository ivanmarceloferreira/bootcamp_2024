create table filme_ator (
    filme_id int not null,
    ator_id int not null
);

alter table filme_ator add constraint PK_filme_ator
primary key (filme_id, ator_id);

alter table filme_ator add constraint FK_filme_filme
foreign key (filme_id) references filmes(id);

alter table filme_ator add constraint FK_filme_ator
foreign key (ator_id) references ator(id);