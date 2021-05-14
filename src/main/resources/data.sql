
INSERT INTO proposta(documento, nome, email, endereco, salario) VALUES
('76654237609','Ramiro','ramiro@gmail.com','Baixada', 25000);

INSERT INTO cartao(numero,emitido_em,titular,limite,proposta_id) VALUES
('8596-6584-2514-5414',NOW(),'Ramiro',1500, 1);