package forohub.apiForoHub.domain.topicos;

import forohub.apiForoHub.domain.enumeracion.Status;
import forohub.apiForoHub.domain.respuesta.RespuestasRepository;
import forohub.apiForoHub.dto.DatosActualizarTopico;
import forohub.apiForoHub.dto.DatosRegistroTopico;
import forohub.apiForoHub.dto.DatosTopico;
import forohub.apiForoHub.domain.usuarios.Usuario;
import forohub.apiForoHub.domain.usuarios.UsuarioRepository;
import forohub.apiForoHub.infra.errores.DuplicatedError;
import forohub.apiForoHub.infra.errores.TopicNotFound;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class TopicoService implements ITopicoService{
    private final TopicoRepository topicRepository;
    private final UsuarioRepository userRepository;
    private final RespuestasRepository answerRepository;

    public TopicoService(TopicoRepository topicRepository, UsuarioRepository userRepository, RespuestasRepository answerRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public ResponseEntity<?> createTopic(DatosRegistroTopico validationDTO, Usuario userFound) {
        Topico topic = new Topico(validationDTO, userFound);
        if (!topicRepository.findByTitle(topic.getTitulo()).isPresent()) {
            topicRepository.save(topic);
            return new ResponseEntity<>(new DatosTopico(topic.getTitulo(),topic.getMensaje(),
                    topic.getAutor().getUsername(),topic.getStatus(),topic.getNombreCurso()), HttpStatus.CREATED);
        } else {
            throw new DuplicatedError("No se pueden crear topicos con títulos iguales");
        }
    }

    @Override
    public Page<DatosTopico> findAllTopics(Pageable pg) {
        var activeTopics = topicRepository.findByStatus(pg, Status.ACTIVO);
        if (!activeTopics.isEmpty()) {
            var response = activeTopics
                    .map(list -> new DatosTopico(list.getTitulo(),list.getMensaje(),list.getAutor().getUsername(),list.getStatus(),list.getNombreCurso()));
            return response;
        } else {
            throw new TopicNotFound("No se encontraron topicos");
        }
    }

    @Override
    public ResponseEntity<?> findTopicById(Long id) {
        var topicFound = topicRepository.findById(id);
        if (topicFound.isPresent()) {
            return ResponseEntity.ok(new DatosTopico(topicFound.get().getTitulo(),topicFound.get().getMensaje(),
                    topicFound.get().getAutor().getUsername(),topicFound.get().getStatus(),topicFound.get().getNombreCurso()));
        } else {
            throw new TopicNotFound("El topico no fue encontrado");
        }
    }

    @Override
    public ResponseEntity<?> updateTopicById(Long id, DatosRegistroTopico validationDTO, Long user_id) {
        var findTopic = topicRepository.findById(id);
        var findUser = userRepository.findById(user_id);
        if (findTopic.isPresent() && findUser.isPresent()) {
            Long idUserTopicOwner = findTopic.get().getAutor().getId();
            Long idUserFound = findUser.get().getId();
            if (idUserTopicOwner.equals(idUserFound)) {
                findTopic.get().updateTopic(validationDTO);
                return ResponseEntity.ok(new DatosActualizarTopico(validationDTO));
            }
        } else {
            throw new TopicNotFound("Usuario o topico inexistente");
        }
        return null;
    }

    @Override
    public ResponseEntity<?> deleteTopicById(Long topic_id) {
        var topicFound = topicRepository.findById(topic_id);
        if (topicFound.isPresent()) {
            answerRepository.removeByTopicId(topic_id);
            topicRepository.removeById(topic_id);
            return ResponseEntity.ok("Se elimino el topico");
        }
        throw new TopicNotFound("El topico no existe");
    }
}
