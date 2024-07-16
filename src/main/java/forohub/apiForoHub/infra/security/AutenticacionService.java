package forohub.apiForoHub.infra.security;

import forohub.apiForoHub.domain.usuarios.UsuarioRepository;
import forohub.apiForoHub.dto.DatosAutenticacionUsuario;
import forohub.apiForoHub.dto.DatosRespuestaLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService  implements UserDetailsService {
   @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private TokenService tokenService;

    public AutenticacionService(UsuarioRepository repository, TokenService tknService) {
        this.usuarioRepository = repository;
        this.tokenService = tknService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username);
    }

    public ResponseEntity<?> loginUser(DatosAutenticacionUsuario userLogin) {
        String user = userLogin.email();
        String password = userLogin.contrasena();
        Authentication authentication = authenticate(user, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenService.generarToken(authentication);
        DatosRespuestaLogin response = new DatosRespuestaLogin(user, "Inicio de sesión exitoso", token);
        return ResponseEntity.ok(response);
    }

    private Authentication authenticate(String user, String password) {
        UserDetails userDetails = loadUserByUsername(user);
        if (userDetails == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Usuario no encontrado");
        }
        return new UsernamePasswordAuthenticationToken(user, userDetails.getPassword());
    }
}
