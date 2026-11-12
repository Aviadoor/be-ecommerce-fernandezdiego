package com.ecommerce.backend.controlador;

import com.ecommerce.backend.dto.ProductoDto;
import com.ecommerce.backend.servicio.ProductoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoControlador {

    private final ProductoServicio servicio;

    public ProductoControlador(ProductoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<ProductoDto> listar() {
        return servicio.listarTodos();
    }

    @GetMapping("/categoria/{categoria}")
    public List<ProductoDto> porCategoria(@PathVariable String categoria) {
        return servicio.filtrarPorCategoria(categoria);
    }

    @GetMapping("/buscar")
    public List<ProductoDto> buscar(@RequestParam String termino) {
        return servicio.buscarSeguro(termino);
    }

    @PostMapping
    public ResponseEntity<ProductoDto> crear(@RequestBody ProductoDto dto) {
        return ResponseEntity.ok(servicio.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> editar(@PathVariable Long id, @RequestBody ProductoDto dto) {
        return ResponseEntity.ok(servicio.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}