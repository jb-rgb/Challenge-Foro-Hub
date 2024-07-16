package forohub.apiForoHub.controllers;

import forohub.apiForoHub.domain.respuesta.IRespuestaService;
import forohub.apiForoHub.domain.respuesta.RespuestasRepository;
import forohub.apiForoHub.dto.RespuestaDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuesta")
public class RespuestaController {
    private IRespuestaService service;

    public RespuestaController(RespuestasRepository repository, IRespuestaService service) {
        this.service = service;
    }
    @PostMapping("user/{user_id}/topic/{topic_id}")
    @Transactional
    public ResponseEntity<?> crearRespuesta(@Valid @RequestBody RespuestaDTO dto, @PathVariable Long user_id, @PathVariable Long topic_id) {
        return service.answerTopic(dto, user_id, topic_id);
    }
}
