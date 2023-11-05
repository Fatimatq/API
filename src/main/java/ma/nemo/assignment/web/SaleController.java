package ma.nemo.assignment.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nemo.assignment.dto.SaleDTO;
import ma.nemo.assignment.exceptions.ProductNotFound;
import ma.nemo.assignment.exceptions.ProductQuantityNotInStock;
import ma.nemo.assignment.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
@Slf4j
public class SaleController {

    private final SaleService saleService;
    @PostMapping
    public ResponseEntity<?> addSale(@Valid @RequestBody SaleDTO saleDTO) throws ProductNotFound, ProductQuantityNotInStock{
        log.info("New Sale for product : " + saleDTO.getProductCode());
        return new ResponseEntity<>(saleService.addSale(saleDTO), HttpStatus.CREATED);
    }

}
