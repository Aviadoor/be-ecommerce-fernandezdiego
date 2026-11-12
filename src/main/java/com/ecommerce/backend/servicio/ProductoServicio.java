package com.ecommerce.backend.servicio;

import com.ecommerce.backend.dto.ProductoDto;
import com.ecommerce.backend.entidad.Producto;
import com.ecommerce.backend.repositorio.ProductoRepositorio;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoServicio {

    private final ProductoRepositorio repositorio;

    public ProductoServicio(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<ProductoDto> listarTodos() {
        return repositorio.findAll().stream().map(this::mapearADto).toList();
    }

    public List<ProductoDto> filtrarPorCategoria(String categoria) {
        return repositorio.buscarPorCategoria(categoria).stream().map(this::mapearADto).toList();
    }

    public List<ProductoDto> buscarSeguro(String termino) {
        return repositorio.buscarPorNombreOCodigoSeguro(termino).stream().map(this::mapearADto).toList();
    }

    public ProductoDto guardar(ProductoDto dto) {
        Producto entidad = new Producto();
        copiarPropiedades(dto, entidad);
        return mapearADto(repositorio.save(entidad));
    }

    public ProductoDto actualizar(Long id, ProductoDto dto) {
        Producto existente = repositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        copiarPropiedades(dto, existente);
        return mapearADto(repositorio.save(existente));
    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    private void copiarPropiedades(ProductoDto dto, Producto p) {
        p.setNombre(dto.nombre());
        p.setCategoria(dto.categoria());
        p.setPrecioCompra(dto.precioCompra());
        p.setPrecioVenta(dto.precioVenta());
        p.setStock(dto.stock());
        p.setMarca(dto.marca());
        p.setCodigoBarras(dto.codigoBarras());
        p.setFechaIngreso(dto.fechaIngreso());
    }

    private ProductoDto mapearADto(Producto p) {
        return new ProductoDto(
            p.getId(), p.getNombre(), p.getCategoria(),
            p.getPrecioCompra(), p.getPrecioVenta(), p.getStock(),
            p.getMarca(), p.getCodigoBarras(), p.getFechaIngreso()
        );
    }
}