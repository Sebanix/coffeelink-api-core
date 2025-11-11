/* ===== V1: Creación de Tablas ===== */

CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    contraseña_hash VARCHAR(255) NOT NULL,
    rol VARCHAR(10) NOT NULL CHECK (rol IN ('admin', 'cliente')),
    fecha_creacion TIMESTAMPTZ DEFAULT NOW()
);

CREATE TABLE productos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    fecha_creacion TIMESTAMPTZ DEFAULT NOW(),
    fecha_actualizacion TIMESTAMPTZ DEFAULT NOW()
);

CREATE TABLE pedidos (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    fecha_pedido TIMESTAMPTZ DEFAULT NOW(),

    CONSTRAINT fk_usuario
        FOREIGN KEY(usuario_id)
            REFERENCES usuarios(id),
    CONSTRAINT fk_producto
        FOREIGN KEY(producto_id)
            REFERENCES productos(id)
);

/* ===== V2: Inserción de Usuarios de Prueba ===== */
/* Contraseña para ambos es "123456" (el hash que generamos) */

INSERT INTO usuarios (nombre, email, contraseña_hash, rol)
VALUES
    ('Admin User', 'admin@coffeelink.com', '$2a$10$CM8JiOaCjulse0whoc8q4.H4A4CA7Z3sqPJc7kzaZfHLmiheHXNIa', 'admin'),
    ('Cliente User', 'cliente@coffeelink.com', '$2a$10$CM8JiOaCjulse0whoc8q4.H4A4CA7Z3sqPJc7kzaZfHLmiheHXNIa', 'cliente');