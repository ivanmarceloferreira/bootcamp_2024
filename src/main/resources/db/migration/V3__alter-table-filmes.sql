ALTER TABLE filmes ADD categoria_id int;

ALTER TABLE filmes add foreign key FK_categoria (categoria_id)
references categorias(id);