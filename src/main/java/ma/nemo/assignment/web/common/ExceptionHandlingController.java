package ma.nemo.assignment.web.common;

import ma.nemo.assignment.exceptions.ProductAlreadyExists;
import ma.nemo.assignment.exceptions.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

//todo fatima add handler and refacto this class by removing reponse entity
//todo handle exception
//todo handle validation exception
@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<String> handleSoldeDisponibleInsuffisantException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Produit non existant", null, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    @ExceptionHandler(ProductAlreadyExists.class)
    public ResponseEntity<String> handleCompteNonExistantException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), null, HttpStatus.UNAUTHORIZED);
    }
}
