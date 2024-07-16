package forohub.apiForoHub.domain.topicos;

import forohub.apiForoHub.dto.DatosRegistroTopico;
import forohub.apiForoHub.dto.DatosTopico;
import forohub.apiForoHub.domain.usuarios.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;

public interface ITopicoService {
    ResponseEntity<?> createTopic(DatosRegistroTopico validationDTO, Usuario user);
    public Page<DatosTopico> findAllTopics(Pageable pg);
    ResponseEntity<?> findTopicById(Long id);
    ResponseEntity<?> updateTopicById(Long id, DatosRegistroTopico validationDTO, Long user_id);
    ResponseEntity<?> deleteTopicById(Long topic_id);
}
