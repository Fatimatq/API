package ma.nemo.assignment.web;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nemo.assignment.dto.ProductDto;
import ma.nemo.assignment.exceptions.ProductAlreadyExists;
import ma.nemo.assignment.exceptions.ProductNotFound;
import ma.nemo.assignment.exceptions.ProductValidationException;
import ma.nemo.assignment.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) throws ProductAlreadyExists, ProductValidationException {
        try {
            ProductDto createdProduct = productService.createProduct(productDto);
            log.info("Created new Product: {}", createdProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (ProductAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (ProductValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        log.info("All products existing in stock: ");
        List<ProductDto> products = productService.listProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) throws ProductNotFound{
        log.info("Show product by Id : " + id);
        Optional<ProductDto> productDto = productService.getProductById(id);
        return new ResponseEntity<>(productDto.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws ProductNotFound{
        productService.deleteProduct(id);
        String response = "Product with id: " + id + " is deleted";
        return new ResponseEntity<>(response, HttpStatus.OK);

    }



}

