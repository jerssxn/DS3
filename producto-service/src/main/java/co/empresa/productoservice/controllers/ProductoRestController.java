@RestController
@RequestMapping("/api/v1/producto-service")
public class ProductoRestController {

    private final IProductoService productoService;
    
    private static final String MENSAJE = "mensaje";
    private static final String PRODUCTO = "producto";
    private static final String PRODUCTOS = "productos";

    public ProductoRestController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/productos")
    public ResponseEntity<Map<String, Object>> getProductos() {
        List<Producto> productos = productoService.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put(PRODUCTOS, productos);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/productos")
    public ResponseEntity<Map<String, Object>> save(@RequestBody Producto producto) {
        Map<String, Object> response = new HashMap<>();
        Producto nuevoProducto = productoService.save(producto);
        response.put(MENSAJE, "El producto ha sido creado con éxito!");
        response.put(PRODUCTO, nuevoProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}