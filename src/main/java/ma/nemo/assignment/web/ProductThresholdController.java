package ma.nemo.assignment.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nemo.assignment.dto.ProductDto;
import ma.nemo.assignment.dto.SetThresholdRequest;
import ma.nemo.assignment.exceptions.ProductNotFound;
import ma.nemo.assignment.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductThresholdController {
    private final ProductService productService;

    @PostMapping("/api/set-threshold")
    public ResponseEntity<?> setThreshold(@Valid @RequestBody SetThresholdRequest thresholdRequest) throws ProductNotFound{
        log.info("set the Threshold of product : " + thresholdRequest.getProductCode());
        productService.setThreshold(thresholdRequest);
        return new ResponseEntity<>("Threshold set successfully", HttpStatus.CREATED);
    }

    @GetMapping("/api/threshold-alerts")
    public ResponseEntity<List<ProductDto>> getAllProductsBelowThreshold(){
        log.info("Show the list of products below Threshold");
        return new ResponseEntity<>(productService.getAllProductsBelowThreshold(), HttpStatus.OK);
    }

}
