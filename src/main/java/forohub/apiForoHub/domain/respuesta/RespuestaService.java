package forohub.apiForoHub.domain.respuesta;

import forohub.apiForoHub.dto.RespuestaDTO;
import forohub.apiForoHub.domain.topicos.TopicoRepository;
import forohub.apiForoHub.domain.usuarios.UsuarioRepository;
import forohub.apiForoHub.infra.errores.TopicNotFound;
import forohub.apiForoHub.infra.errores.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService  implements IRespuestaService {
    private RespuestasRepository respuestaRepository;
    private TopicoRepository topicoRepository;
    private UsuarioRepository usuarioRepository;

    public RespuestaService(
            RespuestasRepository respuestaRepository,
     TopicoRepository topicoRepository,
     UsuarioRepository usuarioRepository
    ) {
        this.respuestaRepository= respuestaRepository;
        this.topicoRepository=topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public ResponseEntity<?> answerTopic(@Valid RespuestaDTO dto, Long user_id, Long topic_id) {
        Respuesta respuesta = new Respuesta(dto, user_id, topic_id);
        var userExists = usuarioRepository.findById(user_id);
        var topicExists = topicoRepository.findById(topic_id);
        if (topicExists.isPresent() && userExists.isPresent()){
            respuestaRepository.save(respuesta);
            return new ResponseEntity<>(new RespuestaDTO(respuesta.getMensaje()),
                    HttpStatus.CREATED);
        } else if(!topicExists.isPresent() && !userExists.isPresent()){
            throw new TopicNotFound("No existe topico y usuario");
        } else if(!topicExists.isPresent()){
            throw new TopicNotFound("No existe el topico");
        } else {
            throw new UserNotFoundException("No existe el usuario");
        }
    }
}
