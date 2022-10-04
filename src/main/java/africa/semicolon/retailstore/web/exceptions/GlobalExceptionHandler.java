package africa.semicolon.retailstore.web.exceptions;

import africa.semicolon.retailstore.dtos.responses.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BuyerEmailAlreadyExistException.class)
    public ResponseEntity<?> handleBuyerEmailAlreadyExistException(BuyerEmailAlreadyExistException buyerEmailAlreadyExistException) {
        ApiResponse apiResponse = ApiResponse.builder()
                .successful(false)
                .message(buyerEmailAlreadyExistException.getMessage())
                .statusCode(400)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BuyerEmailDoesNotExist.class)
    public ResponseEntity<?> handleBuyerEmailDoesNotExist(BuyerEmailDoesNotExist buyerEmailDoesNotExist) {
        ApiResponse apiResponse = ApiResponse.builder()
                .successful(false)
                .message(buyerEmailDoesNotExist.getMessage())
                .statusCode(404)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectLogInException.class)
    public ResponseEntity<?> handleIncorrectLogInException(IncorrectLogInException incorrectLogInException) {
        ApiResponse apiResponse = ApiResponse.builder()
                .successful(false)
                .message(incorrectLogInException.getMessage())
                .statusCode(406)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
    }


    @ExceptionHandler(ProductDoesNotExistException.class)
    public ResponseEntity<?> handleProductDoesNotExistException(ProductDoesNotExistException productDoesNotExistException) {
        ApiResponse apiResponse = ApiResponse.builder()
                .successful(false)
                .message(productDoesNotExistException.getMessage())
                .statusCode(406)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductQuantityOutOfRange.class)
    public ResponseEntity<?> handleProductQuantityOutOfRange(ProductQuantityOutOfRange productQuantityOutOfRange) {
        ApiResponse apiResponse = ApiResponse.builder()
                .successful(false)
                .message(productQuantityOutOfRange.getMessage())
                .statusCode(400)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RetailStoreException.class)
    public ResponseEntity<?> handleRetailStoreException(RetailStoreException retailStoreException) {
        ApiResponse apiResponse = ApiResponse.builder()
                .successful(false)
                .message(retailStoreException.getMessage())
                .statusCode(400)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(JsonPatchException.class)
    public ResponseEntity<?> handleJsonPatchException(JsonPatchException jsonPatchException) {
        ApiResponse apiResponse = ApiResponse.builder()
                .successful(false)
                .message(jsonPatchException.getMessage())
                .statusCode(500)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<?> handleJsonProcessingException(JsonProcessingException jsonProcessingException) {
        ApiResponse apiResponse = ApiResponse.builder()
                .successful(false)
                .message(jsonProcessingException.getMessage())
                .statusCode(500)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
