CREATE TABLE
    Usuario(
        id bigint auto_increment,
        email varchar(100),
        password varchar(300),
        PRIMARY KEY (id)
    );

CREATE TABLE
    Topico (
        id bigint auto_increment,
        titulo varchar(100) UNIQUE,
        mensaje TEXT,
        fecha_creacion timestamp,
        status varchar(50),
        autor varchar(50),
        curso varchar(255)
        user_id bigint,
        PRIMARY KEY (id),
        FOREIGN KEY (usuario_id) references Usuario (id)
    );