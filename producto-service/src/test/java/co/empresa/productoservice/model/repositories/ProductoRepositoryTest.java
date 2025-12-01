package co.empresa.productoservice.model.repositories;

import co.empresa.productoservice.model.entities.Producto;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Pruebas de integración para la capa de Repositorio.
 * DataJpaTest configura un ambiente de prueba para JPA, 
 * generalmente usando una base de datos en memoria (H2)
 * a menos que se configure explícitamente lo contrario.
 */
@DataJpaTest
class ProductoRepositoryTest {

    @Autowired
    IProductoRepository productoRepository;

    @Test
    void whenFindByValidId_thenReturnProducto() {
        // Arrange (Preparar)
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre("Test Laptop");
        nuevoProducto.setDescripcion("Laptop para pruebas");
        nuevoProducto.setPrecio(1000.00);
        Producto savedProducto = productoRepository.save(nuevoProducto);

        // Act (Actuar)
        Optional<Producto> foundProducto = productoRepository.findById(savedProducto.getId());

        // Assert (Afirmar)
        assertThat(foundProducto).isPresent();
        assertThat(foundProducto.get().getNombre()).isEqualTo("Test Laptop");
    }

    @Test
    void whenFindAllWithPagination_thenReturnPagedResult() {
        // Arrange (Preparar)
        productoRepository.deleteAll(); // Limpiar para asegurar resultados predecibles
        for (int i = 1; i <= 5; i++) {
            Producto p = new Producto();
            p.setNombre("Producto " + i);
            p.setPrecio((double) i * 10);
            productoRepository.save(p);
        }
        
        Pageable pageable = PageRequest.of(0, 3); // Pedir la página 0 con 3 elementos

        // Act (Actuar)
        Page<Producto> productPage = (Page<Producto>) productoRepository.findAll(pageable);

        // Assert (Afirmar)
        assertThat(productPage.getTotalElements()).isEqualTo(5);
        assertThat(productPage.getContent()).hasSize(3);
        assertThat(productPage.getNumber()).isEqualTo(0);
    }
    
    @Test
    void whenSaveProducto_thenIdIsGenerated() {
        // Arrange (Preparar)
        Producto producto = new Producto();
        producto.setNombre("Producto Nuevo");
        producto.setPrecio(50.0);

        // Act (Actuar)
        Producto savedProducto = productoRepository.save(producto);

        // Assert (Afirmar)
        assertThat(savedProducto).isNotNull();
        assertThat(savedProducto.getId()).isNotNull();
        assertThat(savedProducto.getId()).isGreaterThan(0);
    }
}

