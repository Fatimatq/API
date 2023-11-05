package ma.nemo.assignment.serviceImplementation;

import lombok.RequiredArgsConstructor;
import ma.nemo.assignment.domain.Product;
import ma.nemo.assignment.domain.Supply;
import ma.nemo.assignment.domain.TransactionHistory;
import ma.nemo.assignment.domain.util.EventType;
import ma.nemo.assignment.dto.ProductDto;
import ma.nemo.assignment.dto.SupplyDTO;
import ma.nemo.assignment.exceptions.ProductNotFound;
import ma.nemo.assignment.mapper.ProductMapper;
import ma.nemo.assignment.mapper.TransactionMapper;
import ma.nemo.assignment.repository.SupplyRepository;
import ma.nemo.assignment.service.ProductService;
import ma.nemo.assignment.service.SupplyService;
import ma.nemo.assignment.service.TransactionHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplyServiceImpl implements SupplyService {
    private final SupplyRepository supplyRepository;
    private final ProductService productService;
    private final TransactionHistoryService transactionHistoryService;
    private final TransactionMapper transactionMapper;
    private final ProductMapper productMapper;

//todo fatima delete product check
    //todo fatima why create history in this service
    @Override
    public SupplyDTO addProductToInventory(SupplyDTO supplyDTO) throws ProductNotFound {
        Product product = productService.getProductByCode(supplyDTO.getProductCode());

        if (product == null) {
            throw new ProductNotFound("Product with code : " +  supplyDTO.getProductCode()+ " not found " );
        }
        Supply supply= new Supply();
        supply.setProduct(product);
        supply.setQuantity(supplyDTO.getQuantity());
        supply.setProductExpirationDate(supplyDTO.getProductExpirationDate());
        supply.setSupplyDate(LocalDateTime.now());

        supplyRepository.save(supply);

        Integer newQuantity = supplyDTO.getQuantity() + product.getQuantityInStock();

        product.setQuantityInStock(newQuantity);
        product.setModificationDate(LocalDateTime.now());

        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setProduct(product);
        transactionHistory.setQuantity(supplyDTO.getQuantity());
        transactionHistory.setTransactionType(EventType.SUPPLY);
        transactionHistory.setTransactionDate(LocalDateTime.now());
        transactionHistory.setUser(null);

        transactionHistoryService.addTransaction(transactionMapper.toDTO(transactionHistory));

        return supplyDTO;
    }

    @Override
    public List<ProductDto> getProductsNearExpiryDate(int thresholdDays) {
        //todo maybe serch by date less then end
        LocalDate currentDate = LocalDate.now();
        LocalDateTime thresholdStartDate = LocalDate.now().atStartOfDay(); // Start of the current day
        LocalDateTime thresholdEndDate = currentDate.plusDays(thresholdDays).atTime(23, 59, 59); // End of the specified day

        List<Supply> supplies = supplyRepository.findByProductExpirationDateBetween(thresholdStartDate, thresholdEndDate);

        return supplies.stream()
                .map(supply -> productMapper.toDTO(supply.getProduct()))
                .collect(Collectors.toList());
    }



}
