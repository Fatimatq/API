package ma.nemo.assignment.serviceImplementation;

import lombok.RequiredArgsConstructor;
import ma.nemo.assignment.domain.Product;
import ma.nemo.assignment.dto.ProductDto;
import ma.nemo.assignment.dto.SetThresholdRequest;
import ma.nemo.assignment.exceptions.ProductAlreadyExists;
import ma.nemo.assignment.exceptions.ProductNotFound;
import ma.nemo.assignment.exceptions.ProductValidationException;
import ma.nemo.assignment.mapper.ProductMapper;
import ma.nemo.assignment.repository.ProductRepository;
import ma.nemo.assignment.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) throws ProductAlreadyExists, ProductValidationException {
        Product product = productRepository.findByProductCode(productDto.getProductCode());
        if(product != null){
            throw new ProductAlreadyExists("Product with code :" + productDto.getProductCode()+" already exist");
        }
        Product newProduct = new Product();
        newProduct.setProductCode(productDto.getProductCode());
        newProduct.setProductName(productDto.getProductName());
        newProduct.setDescription(productDto.getDescription());
        newProduct.setQuantityInStock(productDto.getQuantityInStock());
        newProduct.setUnitPrice(productDto.getUnitPrice());
        newProduct.setQuantityThreshold(productDto.getQuantityThreshold());

        LocalDateTime currentDateTime= LocalDateTime.now();
        newProduct.setCreationDate(currentDateTime);
        newProduct.setModificationDate(currentDateTime);

       productRepository.save(newProduct);
       return productMapper.toDTO(newProduct);

    }

    @Override
    public List<ProductDto> listProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }
//todo fatima why optional and throw if not exit
    //todo fatima replace sout by log
    @Override
    public Optional<ProductDto> getProductById(Long id) throws ProductNotFound {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            ProductDto productDto = productMapper.toDTO(product.get());
            return Optional.of(productDto);
        }
        throw new ProductNotFound("Product with id: " + id + " not found");
    }

    @Override
    public Product getProductByCode(String productCode) throws ProductNotFound {
        return productRepository.findByProductCode(productCode)
                .orElseThrow(()->new ProductNotFound("Product with id: " + productCode + " not found"));
    }

    @Override
    public boolean deleteProduct(Long id) throws ProductNotFound {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            System.out.println("Product with id: " + id + " is deleted");
            return true;
        } else {
            throw new ProductNotFound("Product with id: " + id + " not found");
        }
    }

    @Override
    public void setThreshold(SetThresholdRequest thresholdRequest) throws ProductNotFound {
        Product product= productRepository.findByProductCode(thresholdRequest.getProductCode());
        product.setQuantityThreshold(thresholdRequest.getQuantityThreshold());
        productRepository.save(product);
    }

    @Override
    public List<ProductDto> getAllProductsBelowThreshold() {
        List<Product> products = productRepository.findProductsBelowThreshold();
        if(products.isEmpty()){
            return null;
        }
        else {
            return products.stream()
                    .map(productMapper::toDTO)
                    .collect(Collectors.toList());
        }
    }

}
