package ma.nemo.assignment.exceptions;

import java.io.Serial;

public class ProductAlreadyExists extends Exception {

  @Serial
  private static final long serialVersionUID = 1L;

  public ProductAlreadyExists() {
  }

  public ProductAlreadyExists(String message) {
    super(message);
  }
}
