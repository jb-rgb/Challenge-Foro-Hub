package forohub.apiForoHub.dto;

import forohub.apiForoHub.domain.enumeracion.Status;

public record DatosTopico(
        String titulo,
        String mensaje,
        String nombreUsuario,
        Status status,
        String nombreCurso
) {
}