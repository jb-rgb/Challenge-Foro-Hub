package forohub.apiForoHub.domain.respuesta;

import forohub.apiForoHub.domain.enumeracion.Resuelto;
import forohub.apiForoHub.dto.RespuestaDTO;
import forohub.apiForoHub.domain.topicos.Topico;
import forohub.apiForoHub.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "respuestas")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;

    @ManyToOne
    private Topico topico;

    @Column(name = "fecha_creacion")
    private LocalDate fecha;

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

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Resuelto getResuelto() {
        return resuelto;
    }

    public void setResuelto(Resuelto resuelto) {
        this.resuelto = resuelto;
    }

    @OneToOne
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    private Resuelto resuelto;

    public Respuesta(RespuestaDTO dtoRespuesta,  Long topico_id,Long usuario_id) {
        this.mensaje = dtoRespuesta.mensaje();
        this.topico = new Topico(topico_id);
        this.fecha = LocalDate.now();
        this.usuario = new Usuario(usuario_id);
        this.resuelto = Resuelto.NO;
    }
}
