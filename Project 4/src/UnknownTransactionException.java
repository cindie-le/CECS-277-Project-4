public class UnknownTransactionException extends IllegalArgumentException {
    public UnknownTransactionException() {}

    public UnknownTransactionException(String message) {
        super(message);
    }
}
