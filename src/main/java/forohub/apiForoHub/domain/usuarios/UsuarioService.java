package forohub.apiForoHub.domain.usuarios;

import forohub.apiForoHub.dto.DatosAutenticacionUsuario;
import forohub.apiForoHub.dto.DatosUsuario;
import forohub.apiForoHub.infra.errores.DuplicatedError;
import forohub.apiForoHub.infra.errores.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

@Service
public class UsuarioService implements  IUsuarioService{
    private UsuarioRepository repository;
    private PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public ResponseEntity<?> register(DatosAutenticacionUsuario validationDTO) {
        var username = repository.findByEmail(validationDTO.email());
        if (username == null) {
            throw new DuplicatedError("El usuario " + validationDTO.email()+ " ya existe");
        } else {
            String encodedPassword = encoder.encode(validationDTO.contrasena());
            Usuario user = repository.save(new Usuario(validationDTO, encodedPassword));
            return new ResponseEntity<>(new DatosUsuario(user), HttpStatus.CREATED);
        }
    }

    @Override
    public Usuario findUserToCreateTopic(Long id) {
        var userFound = repository.findById(id);
        if (userFound.isPresent()) {
            return userFound.get();
        } else {
            throw new UserNotFoundException("El id " + id + " no existe en la base de datos");
        }
    }
}
