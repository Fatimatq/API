package ma.nemo.assignment.service;

import ma.nemo.assignment.domain.Product;
import ma.nemo.assignment.dto.ProductDto;
import ma.nemo.assignment.dto.SetThresholdRequest;
import ma.nemo.assignment.exceptions.ProductAlreadyExists;
import ma.nemo.assignment.exceptions.ProductNotFound;
import ma.nemo.assignment.exceptions.ProductValidationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    ProductDto createProduct(ProductDto productDto) throws ProductAlreadyExists, ProductValidationException;

    List<ProductDto> listProducts();

    Optional<ProductDto> getProductById(Long id) throws ProductNotFound;
    Product getProductByCode(String productCode) throws ProductNotFound;

    boolean deleteProduct(Long id) throws ProductNotFound;

    void setThreshold(SetThresholdRequest thresholdRequest) throws ProductNotFound;

    List<ProductDto> getAllProductsBelowThreshold();
}
