package ma.nemo.assignment.serviceImplementation;

import lombok.RequiredArgsConstructor;
import ma.nemo.assignment.domain.Product;
import ma.nemo.assignment.domain.Sale;
import ma.nemo.assignment.domain.TransactionHistory;
import ma.nemo.assignment.domain.util.EventType;
import ma.nemo.assignment.dto.SaleDTO;
import ma.nemo.assignment.exceptions.ProductNotFound;
import ma.nemo.assignment.exceptions.ProductQuantityNotInStock;
import ma.nemo.assignment.mapper.SaleMapper;
import ma.nemo.assignment.mapper.TransactionMapper;
import ma.nemo.assignment.repository.SaleRepository;
import ma.nemo.assignment.service.ProductService;
import ma.nemo.assignment.service.SaleService;
import ma.nemo.assignment.service.TransactionHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final ProductService productService;
    private final SaleRepository saleRepository;
    private final TransactionHistoryService transactionHistoryService;
    private final TransactionMapper transactionMapper;
    private final SaleMapper saleMapper;

    @Override
    public SaleDTO addSale(SaleDTO saleDTO) throws ProductNotFound, ProductQuantityNotInStock {
        Product product = productService.getProductByCode(saleDTO.getProductCode());
        if (product.getQuantityInStock() < saleDTO.getQuantity()){
            throw new ProductQuantityNotInStock("The requested quantity is not available in the stock");
        }
        Sale sale= new Sale();
        sale.setProduct(product);
        sale.setUser(null);
        sale.setSoldQuantity(saleDTO.getQuantity());
        sale.setSaleDate(LocalDateTime.now());
        sale.setTotalPrice(product.getUnitPrice() * saleDTO.getQuantity());
        saleRepository.save(sale);

        product.setModificationDate(LocalDateTime.now());
        product.setQuantityInStock(product.getQuantityInStock() - saleDTO.getQuantity());

        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setProduct(product);
        transactionHistory.setQuantity(saleDTO.getQuantity());
        transactionHistory.setTransactionType(EventType.SALE);
        transactionHistory.setTransactionDate(LocalDateTime.now());
        transactionHistory.setUser(null);

        transactionHistoryService.addTransaction(transactionMapper.toDTO(transactionHistory));
        return saleMapper.toDTO(sale);
    }
}
