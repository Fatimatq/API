package ma.nemo.assignment.service;

import ma.nemo.assignment.dto.ProductDto;
import ma.nemo.assignment.dto.SupplyDTO;
import ma.nemo.assignment.exceptions.ProductNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplyService {
    SupplyDTO addProductToInventory(SupplyDTO supplyDTO) throws ProductNotFound;

    List<ProductDto> getProductsNearExpiryDate(int thresholdDays);
}
