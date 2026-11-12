package com.ecommerce.backend.repositorio;

import com.ecommerce.backend.entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

    // Ejecuta automáticamente el @NamedQuery declarado en la entidad Producto
    List<Producto> buscarPorCategoria(@Param("categoria") String categoria);

    // Consulta JPQL con parámetros seguros contra inyección
    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :termino, '%')) OR p.codigoBarras = :termino")
    List<Producto> buscarPorNombreOCodigoSeguro(@Param("termino") String termino);
}