package forohub.apiForoHub.domain.topicos;

import forohub.apiForoHub.domain.enumeracion.Status;
import forohub.apiForoHub.domain.respuesta.Respuesta;
import forohub.apiForoHub.dto.DatosRegistroTopico;
import forohub.apiForoHub.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topico")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private String nombreCurso;
    private String titulo;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario  autor;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "topic_id")
    private List<Respuesta> respuestas;

    public Topico(DatosRegistroTopico topico, Usuario usuario){
        this.mensaje = topico.mensaje();
        this.titulo = topico.titulo();
        this.nombreCurso= topico.nombreCurso();
        this.fechaCreacion = LocalDate.now().atStartOfDay();
        this.status = Status.ACTIVO;
        this.autor = usuario;
    }

    public Topico(Long topic_id) {
        this.id = topic_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void updateTopic(DatosRegistroTopico validationDTO) {
        if (!validationDTO.titulo().isBlank()) {
            this.titulo = validationDTO.titulo();
        }
        if (!validationDTO.mensaje().isBlank()) {
            this.mensaje = validationDTO.mensaje();
        }
        if (!validationDTO.nombreCurso().isBlank()) {
            this.nombreCurso = validationDTO.nombreCurso();
        }
    }
}
