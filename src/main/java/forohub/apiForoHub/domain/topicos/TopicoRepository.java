package forohub.apiForoHub.domain.topicos;

import forohub.apiForoHub.domain.enumeracion.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByStatus(Pageable pg, Status status);
    Optional<Topico> findByTitle(String title);
    void removeById(Long id);
}
