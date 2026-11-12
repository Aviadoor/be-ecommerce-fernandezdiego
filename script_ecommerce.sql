CREATE DATABASE IF NOT EXISTS db_ecommerce_fernandezdiego;
USE db_ecommerce_fernandezdiego;

CREATE TABLE IF NOT EXISTS productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL,
    categoria VARCHAR(80) NOT NULL,
    precio_compra DECIMAL(10,2) NOT NULL,
    precio_venta DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    marca VARCHAR(80) NOT NULL,
    codigo_barras VARCHAR(60) NOT NULL UNIQUE,
    fecha_ingreso DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(60) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(30) NOT NULL
);