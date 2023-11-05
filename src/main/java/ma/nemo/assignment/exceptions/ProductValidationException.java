package ma.nemo.assignment.exceptions;

import java.io.Serial;

public class ProductValidationException extends Exception {

  @Serial
  private static final long serialVersionUID = 1L;

  public ProductValidationException() {
  }

  public ProductValidationException(String message) {
    super(message);
  }
}
