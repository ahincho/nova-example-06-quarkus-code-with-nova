package pe.edu.nova;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pe.edu.nova.java.libs.api.standard.error.ApplicationError;
import pe.edu.nova.java.libs.api.standard.response.ApiResponse;

import java.util.List;

/**
 * Recurso REST de ejemplo que demuestra la integración con
 * {@code nova-api-standard-quarkus-extension}: el recurso arma el
 * {@link ApiResponse} y la extensión lo deja pasar tal cual, y los errores de
 * Nova (por ejemplo un {@link ApplicationError}) se serializan también como
 * {@code ApiResponse}, con el código HTTP de su capa y un {@code traceId}.
 * Una excepción inesperada, como una {@link IllegalArgumentException}, ya no es
 * un 400: responde un 500 con el mensaje genérico del catálogo.
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
        // Una entrada inválida se lanza como error de Nova: una IllegalArgumentException
        // respondería un 500, no un 400.
        throw ApplicationError.invalidInput("Error de validación simulado", List.of());
    }
}
