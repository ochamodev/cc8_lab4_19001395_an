package CCVIII.Lab04.model;

public class ServerResult<T> {
    private final T value;
    private final Exception exception;

    private ServerResult(T value, Exception exception) {
        this.value = value;
        this.exception = exception;
    }

    public static <T> ServerResult<T> success(T value) {
        return new ServerResult<>(value, null);
    }

    public static <T> ServerResult<T> failure(Exception exception) {
        return new ServerResult<>(null, exception);
    }

    public boolean isSuccess() {
        return exception == null;
    }

    public T getValue() {
        if (exception != null) {
        }
        return value;
    }

    public Exception getException() {
        return exception;
    }
}
