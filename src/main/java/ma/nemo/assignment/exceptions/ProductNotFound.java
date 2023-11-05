package ma.nemo.assignment.exceptions;

import java.io.Serial;

public class ProductNotFound extends Exception {

  @Serial
  private static final long serialVersionUID = 1L;

  public ProductNotFound() {
  }

  public ProductNotFound(String message) {
    super(message);
  }
}
