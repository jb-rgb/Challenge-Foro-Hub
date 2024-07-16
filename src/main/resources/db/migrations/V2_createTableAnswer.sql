CREATE TABLE
    Answer (
        id bigint auto_increment,
        mensaje TEXT,
        topico_id bigint,
        fecha_creacion timestamp,
        usuario_id bigint,
        resuelto boolean,
        PRIMARY KEY (id),
        FOREIGN KEY (usuario_id) references Usuario (id),
        FOREIGN KEY (topico_id) references Topico (id)
    );