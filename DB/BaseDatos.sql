CREATE TABLE persona
(
    id             SERIAL       NOT NULL,
    nombre         VARCHAR(255) NOT NULL,
    genero         VARCHAR(255) NOT NULL,
    edad           INTEGER      NOT NULL,
    identificacion VARCHAR(255) NOT NULL,
    direccion      VARCHAR(255) NOT NULL,
    telefono       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_persona PRIMARY KEY (id)
);

CREATE TABLE cliente
(
    clienteid  SERIAL       NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    estado     BOOLEAN      NOT NULL,
    persona_id INT       NOT NULL,
    CONSTRAINT pk_cliente PRIMARY KEY (clienteid)
);

ALTER TABLE cliente
    ADD CONSTRAINT uc_cliente_persona UNIQUE (persona_id);

ALTER TABLE cliente
    ADD CONSTRAINT FK_CLIENTE_ON_PERSONA FOREIGN KEY (persona_id) REFERENCES persona (id);

CREATE TABLE cuenta
(
    id            SERIAL           NOT NULL,
    numero_cuenta VARCHAR(255)     NOT NULL,
    tipo_cuenta   VARCHAR(255)     NOT NULL,
    saldo_inicial BIGINT		   NOT NULL,
    estado        BOOLEAN          NOT NULL,
    cliente_id    INT           NOT NULL,
    CONSTRAINT pk_cuenta PRIMARY KEY (id)
);


ALTER TABLE cuenta
    ADD CONSTRAINT FK_CUENTA_ON_USUARIO FOREIGN KEY (cliente_id) REFERENCES cliente (clienteid);

CREATE TABLE movimiento
(
    id              SERIAL                      NOT NULL,
    fecha           TIMESTAMP					NOT NULL,
    tipo_movimiento VARCHAR(255)                NOT NULL,
    valor           BIGINT             			NOT NULL,
    saldo           BIGINT 						NOT NULL,
    cuenta_id       INT                      NOT NULL,
    CONSTRAINT pk_movimiento PRIMARY KEY (id)
);

ALTER TABLE movimiento
    ADD CONSTRAINT FK_MOVIMIENTO_ON_CUENTA FOREIGN KEY (cuenta_id) REFERENCES cuenta (id);