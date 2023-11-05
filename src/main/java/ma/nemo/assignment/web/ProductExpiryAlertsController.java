package ma.nemo.assignment.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nemo.assignment.dto.ProductDto;
import ma.nemo.assignment.service.SupplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/expiry-alerts")
@RequiredArgsConstructor
@Slf4j
public class ProductExpiryAlertsController {
    private final SupplyService supplyService;
    @GetMapping
    public ResponseEntity<List<ProductDto>> listProductsNearExpiryDate(@RequestParam int thresholdDays) {
        log.info("Listing products nearing their date");
        List<ProductDto> productsNearExpiry = supplyService.getProductsNearExpiryDate(thresholdDays);
        return new ResponseEntity<>(productsNearExpiry, HttpStatus.OK);
    }

}
