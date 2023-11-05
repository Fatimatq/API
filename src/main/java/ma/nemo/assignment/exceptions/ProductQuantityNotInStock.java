package ma.nemo.assignment.exceptions;

import java.io.Serial;

public class ProductQuantityNotInStock extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;
    public ProductQuantityNotInStock(){

    }
    public ProductQuantityNotInStock(String message) {
        super(message);
    }
}
