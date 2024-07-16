package forohub.apiForoHub.domain.respuesta;

import forohub.apiForoHub.dto.RespuestaDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface IRespuestaService {
    public ResponseEntity<?> answerTopic(@Valid RespuestaDTO dto, Long user_id, Long topic_id);
}
