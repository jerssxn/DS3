package co.empresa.productoservice.model.services;

import co.empresa.productoservice.model.entities.Producto;
import co.empresa.productoservice.model.repositories.IProductoRepository;
import co.empresa.productoservice.model.services.ProductoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para la capa de Servicio (Business Logic).
 * Se utiliza Mockito para simular (mockear) la capa de Repositorio.
 */
@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    // Simula la dependencia IProductoRepository
    @Mock
    private IProductoRepository productoRepository;

    // Inyecta los mocks necesarios en la clase ProductoServiceImpl
    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto productoEjemplo;

    @BeforeEach
    void setUp() {
        productoEjemplo = new Producto();
        productoEjemplo.setId(1L);
        productoEjemplo.setNombre("Monitor Test");
        productoEjemplo.setPrecio(200.0);
    }

    @Test
    void whenSaveProducto_shouldReturnSavedProducto() {
        // Arrange
        // Cuando se llame a productoRepository.save(cualquier Producto), retorna productoEjemplo
        when(productoRepository.save(any(Producto.class))).thenReturn(productoEjemplo);

        // Act
        Producto saved = productoService.save(new Producto());

        // Assert
        assertThat(saved).isNotNull();
        assertThat(saved.getNombre()).isEqualTo("Monitor Test");
        
        // Verifica que el método save() del repositorio fue llamado exactamente una vez
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    void whenFindById_shouldReturnProducto() {
        // Arrange
        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoEjemplo));

        // Act
        Producto found = productoService.findById(1L);

        // Assert
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(1L);
    }

    @Test
    void whenFindByIdNotFound_shouldReturnNull() {
        // Arrange
        when(productoRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        Producto found = productoService.findById(999L);

        // Assert
        assertThat(found).isNull();
    }

    @Test
    void whenFindAll_shouldReturnProductList() {
        // Arrange
        Producto producto2 = new Producto();
        producto2.setId(2L);
        producto2.setNombre("Producto 2");
        producto2.setPrecio(150.0);
        
        List<Producto> productos = Arrays.asList(productoEjemplo, producto2);
        
        when(productoRepository.findAll()).thenReturn(productos);

        // Act
        List<Producto> result = productoService.findAll();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNombre()).isEqualTo("Monitor Test");
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void whenDeleteProducto_shouldCallDeleteMethod() {
        // Arrange
        // No se necesita mockear un retorno para un método void
        doNothing().when(productoRepository).delete(any(Producto.class));

        // Act
        productoService.delete(productoEjemplo);

        // Assert
        // Verifica que el método delete() del repositorio fue llamado con el producto correcto
        verify(productoRepository, times(1)).delete(productoEjemplo);
    }

    @Test
    void whenUpdateProducto_shouldReturnUpdatedProducto() {
        // Arrange
        Producto productoToUpdate = new Producto();
        productoToUpdate.setId(1L);
        productoToUpdate.setNombre("Monitor Actualizado");
        productoToUpdate.setPrecio(250.0);
        
        when(productoRepository.save(any(Producto.class))).thenReturn(productoToUpdate);

        // Act
        Producto updated = productoService.update(productoToUpdate);

        // Assert
        assertThat(updated).isNotNull();
        assertThat(updated.getNombre()).isEqualTo("Monitor Actualizado");
        assertThat(updated.getPrecio()).isEqualTo(250.0);
        verify(productoRepository, times(1)).save(productoToUpdate);
    }
}

