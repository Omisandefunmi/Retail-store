package africa.semicolon.retailstore.web.exceptions;

public class ProductDoesNotExistException extends RetailStoreException {
    public ProductDoesNotExistException(String message) {
        super(message);
    }
}
