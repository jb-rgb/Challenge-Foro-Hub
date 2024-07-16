package forohub.apiForoHub.infra.errores;

public class TopicNotFound  extends RuntimeException {
    private DetallesError error;

    public TopicNotFound(String message) {
        this.error = new DetallesError(message);
    }
    public DetallesError getError() {
        return error;
    }
}
