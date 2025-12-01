
---

1. **¿Qué problema busca resolver Clean Architecture en el desarrollo de software?**
   Busca mantener una separación clara entre la lógica del negocio y los detalles de implementación, para evitar dependencias innecesarias y facilitar la evolución del sistema sin romper funcionalidades existentes.

2. **¿Cuáles son las principales capas de Clean Architecture y qué responsabilidad tiene cada una?**

   * **Domain:** contiene las entidades y reglas puras del negocio.
   * **Application:** define los casos de uso que representan la lógica de aplicación.
   * **Adapters (Interfaces):** convierte los datos entre el dominio y las fuentes externas.
   * **Infrastructure:** maneja la implementación concreta de tecnologías externas, como bases de datos o servicios REST.

3. **¿Qué relación tiene Clean Architecture con el principio de Inversión de Dependencias (DIP) en SOLID?**
   Ambas promueven que las capas de alto nivel no dependan de los detalles, sino de abstracciones, asegurando que los cambios en la infraestructura no afecten la lógica central.

4. **¿Por qué es importante desacoplar la lógica de negocio de la infraestructura en una aplicación?**
   Porque garantiza que el sistema pueda evolucionar, probarse y adaptarse a nuevos requerimientos tecnológicos sin reescribir el núcleo del negocio.

5. **¿Cómo Clean Architecture facilita la escalabilidad y mantenibilidad de un sistema?**
   Gracias a su estructura modular y orientada a interfaces, se pueden añadir nuevas funcionalidades o tecnologías sin alterar el resto del código, manteniendo el sistema ordenado y predecible.

6. **¿Qué diferencia hay entre la capa de dominio y la capa de aplicación en Clean Architecture?**
   El **dominio** se enfoca en qué hace el sistema (reglas de negocio), mientras que la **aplicación** se centra en cómo se logra (procesos y coordinación entre componentes).

7. **¿Por qué los controladores (controllers) y la base de datos deben estar en la capa de infraestructura?**
   Porque representan mecanismos externos que pueden variar según el entorno; deben implementarse como detalles que dependen del dominio, no al revés.

8. **¿Qué ventajas tiene usar una interfaz en la capa de dominio para definir repositorios en lugar de usar directamente JpaRepository?**
   Permite definir contratos genéricos independientes de JPA, posibilitando implementar repositorios con cualquier tecnología (SQL, NoSQL, API externa) sin modificar el dominio.

9. **¿Cómo interactúan los casos de uso (UseCases) con las entidades de dominio en Clean Architecture?**
   Los casos de uso ejecutan operaciones del negocio manipulando entidades del dominio y delegando la persistencia o integración a través de interfaces abstractas.

10. **¿Cómo se puede aplicar Clean Architecture en una aplicación de microservicios con Spring Boot?**
    Organizando cada microservicio con su propio conjunto de capas (dominio, aplicación, infraestructura), aplicando principios de inversión de dependencias y comunicación mediante interfaces o eventos.

---


