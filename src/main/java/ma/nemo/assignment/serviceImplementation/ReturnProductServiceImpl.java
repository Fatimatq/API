package ma.nemo.assignment.serviceImplementation;

import lombok.RequiredArgsConstructor;
import ma.nemo.assignment.domain.Product;
import ma.nemo.assignment.domain.ReturnProduct;
import ma.nemo.assignment.domain.TransactionHistory;
import ma.nemo.assignment.domain.util.EventType;
import ma.nemo.assignment.dto.ReturnProductDTO;
import ma.nemo.assignment.exceptions.ProductNotFound;
import ma.nemo.assignment.mapper.ReturnProductMapper;
import ma.nemo.assignment.mapper.TransactionMapper;
import ma.nemo.assignment.repository.ReturnProductRepository;
import ma.nemo.assignment.service.ProductService;
import ma.nemo.assignment.service.ReturnProductService;
import ma.nemo.assignment.service.TransactionHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReturnProductServiceImpl implements ReturnProductService {
    private final ReturnProductRepository returnProductRepository;
    private final ProductService productService;
    private final TransactionHistoryService transactionHistoryService;
    private final TransactionMapper transactionMapper;
    private final ReturnProductMapper returnProductMapper;
    @Override
    public ReturnProductDTO returnProduct(ReturnProductDTO returnProductDTO) throws ProductNotFound {
        Product product = productService.getProductByCode(returnProductDTO.getProductCode());
        if (product == null) {
            throw new ProductNotFound("Product with code : " +  returnProductDTO.getProductCode()+ " not found " );
        }
        ReturnProduct returnProduct = new ReturnProduct();
        returnProduct.setProduct(product);
        returnProduct.setReturnDate(LocalDateTime.now());
        returnProduct.setUser(null);
        returnProduct.setReason(returnProductDTO.getReason());
        returnProduct.setReturnQuantity(returnProductDTO.getQuantity());

        returnProductRepository.save(returnProduct);

        Integer newQuantity = returnProductDTO.getQuantity() + product.getQuantityInStock();
        product.setQuantityInStock(newQuantity);
        product.setModificationDate(LocalDateTime.now());

        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setProduct(product);
        transactionHistory.setQuantity(returnProductDTO.getQuantity());
        transactionHistory.setTransactionType(EventType.RETURN);
        transactionHistory.setTransactionDate(LocalDateTime.now());
        transactionHistory.setUser(null);

        transactionHistoryService.addTransaction(transactionMapper.toDTO(transactionHistory));
        return returnProductMapper.toDTO(returnProduct);
    }
}
