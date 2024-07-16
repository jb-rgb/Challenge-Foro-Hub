package forohub.apiForoHub.infra.errores;

public class DuplicatedError extends RuntimeException {
    private DetallesError error;

    public DuplicatedError(String message) {
        this.error = new DetallesError(message);
    }

    public DetallesError getError(){
        return error;
    }
}
