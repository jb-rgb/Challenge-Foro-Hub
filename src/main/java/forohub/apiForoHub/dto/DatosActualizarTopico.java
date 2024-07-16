package forohub.apiForoHub.dto;

import lombok.Data;

@Data
public class DatosActualizarTopico {

    private String titulo, mensaje, nombreCurso;

    public DatosActualizarTopico(DatosRegistroTopico validationDTO) {
        if (!validationDTO.titulo().isBlank()) {
            this.titulo = validationDTO.titulo();
        } else {
            this.titulo = "No se ha modificado el título";
        }
        if (!validationDTO.mensaje().isBlank()) {
            this.mensaje= validationDTO.mensaje();
        } else {
            this.mensaje = "No se ha modificado el mensaje";
        }
        if (!validationDTO.nombreCurso().isBlank()) {
            this.nombreCurso = validationDTO.nombreCurso();
        } else {
            this.nombreCurso = "No se ha modificado el curso";
        }
    }
}
