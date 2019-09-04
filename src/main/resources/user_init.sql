-- INSERINDO USUARIO PROFESSOR 
-- login = admin@gmail.com, senha=123123
INSERT INTO public.t_usuario(id, email, nome, password, username)
    VALUES (1,'admin@gmail', 'professor', '$2a$10$DtsJxTf1KIv.I.orEqvoUey55MDjun7Lpmyg2e1ed5PMzT.lRUdgG', 'admin@gmail.com');

INSERT INTO public.t_usuario_permissao(
            id_usuario, permissao)
    VALUES (1, 'ROLE_PROFESSOR');

-- INSERINDO USUARIO PARTICIPANTE 
-- login = participante@gmail, senha=123123
INSERT INTO public.t_usuario(id, email, nome, password, username)
    VALUES (2,'participante@gmail', 'participante', '$2a$10$DtsJxTf1KIv.I.orEqvoUey55MDjun7Lpmyg2e1ed5PMzT.lRUdgG', 'participante@gmail.com');

INSERT INTO public.t_usuario_permissao(
            id_usuario, permissao)
    VALUES (2, 'ROLE_PARTICIPANTE');
