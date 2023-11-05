package ma.nemo.assignment.service;

import ma.nemo.assignment.dto.SaleDTO;
import ma.nemo.assignment.exceptions.ProductNotFound;
import ma.nemo.assignment.exceptions.ProductQuantityNotInStock;
import org.springframework.stereotype.Service;

@Service
public interface SaleService {
    SaleDTO addSale(SaleDTO saleDTO) throws ProductNotFound, ProductQuantityNotInStock;
}
