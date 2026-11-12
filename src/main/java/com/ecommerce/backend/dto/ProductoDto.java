package com.ecommerce.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductoDto(
    Long id,
    String nombre,
    String categoria,
    BigDecimal precioCompra,
    BigDecimal precioVenta,
    Integer stock,
    String marca,
    String codigoBarras,
    LocalDate fechaIngreso
) {}