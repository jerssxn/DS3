package co.empresa.productoservice.controllers;

import co.empresa.productoservice.model.entities.Producto;
import co.empresa.productoservice.model.services.IProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Pruebas unitarias/de integración ligera para la capa de Controlador (REST API).
 * Se utiliza @WebMvcTest para configurar solo el contexto de Spring MVC.
 */
@WebMvcTest(ProductoRestController.class)
class ProductoRestControllerTest {

    @Autowired
    private MockMvc mockMvc; // Permite enviar peticiones HTTP simuladas

    @Autowired
    private ObjectMapper objectMapper; // Para convertir objetos Java a JSON

    @MockBean
    private IProductoService productoService; // Simula la capa de servicio

    @Test
    void whenPostProducto_shouldCreateAndReturnProduct() throws Exception {
        // Arrange (Preparar)
        Producto productoPeticion = new Producto();
        productoPeticion.setNombre("Teclado Test");
        productoPeticion.setDescripcion("Para testing");
        productoPeticion.setPrecio(50.0);

        Producto productoRespuesta = new Producto();
        productoRespuesta.setId(1L);
        productoRespuesta.setNombre("Teclado Test");
        productoRespuesta.setPrecio(50.0);

        // Mock: Cuando el servicio es llamado para guardar, devuelve el producto con ID
        when(productoService.save(any(Producto.class))).thenReturn(productoRespuesta);

        // Act & Assert (Actuar y Afirmar)
        mockMvc.perform(post("/api/v1/producto-service/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productoPeticion)))
                // 1. Verifica el código de estado HTTP
                .andExpect(status().isCreated()) // Espera HttpStatus 201 (CREATED)
                // 2. Verifica el contenido de la respuesta JSON
                .andExpect(jsonPath("$.mensaje").value("El producto ha sido creado con éxito!"))
                .andExpect(jsonPath("$.producto.nombre").value("Teclado Test"))
                .andExpect(jsonPath("$.producto.id").value(1L));
    }
}

