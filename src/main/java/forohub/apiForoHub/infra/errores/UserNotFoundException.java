package forohub.apiForoHub.infra.errores;

public class UserNotFoundException extends RuntimeException {
    private DetallesError error;

    public UserNotFoundException(String message) {
        this.error = new DetallesError(message);
    }
    public DetallesError getError() {
        return error;
    }
}
