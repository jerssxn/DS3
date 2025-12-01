package com.example.pedidoserviceasync;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @GetMapping("/crear")
    public String crearPedido() {
        return "El pedido está en proceso y se recibirá en cuanto llegue el mensaje asíncrono.";
    }

}

