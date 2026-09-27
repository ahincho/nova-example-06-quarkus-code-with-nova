package pe.edu.nova;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pe.edu.nova.java.libs.api.standard.response.ApiResponse;

/**
 * Recurso REST de ejemplo que demuestra la integracion con
 * {@code nova-api-standard-quarkus-extension}: las respuestas se envuelven
 * automaticamente en {@link ApiResponse} y las excepciones no manejadas
 * (e.g. {@link IllegalArgumentException}) se serializan tambien como
 * {@code ApiResponse} con el codigo HTTP apropiado.
 */
@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse<String> hello() {
        return ApiResponse.<String>builder()
                .data("Hello from Quarkus REST")
                .build();
    }

    @GET
    @Path("/boom")
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse<String> boom() {
        throw new IllegalArgumentException("simulated validation error");
    }
}
