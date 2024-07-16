package forohub.apiForoHub.dto;

public record DatosRespuestaLogin(
        String email,
        String status,
        String jwt_token
) {
}
