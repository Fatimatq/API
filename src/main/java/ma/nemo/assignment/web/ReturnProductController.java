package ma.nemo.assignment.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nemo.assignment.dto.ReturnProductDTO;
import ma.nemo.assignment.exceptions.ProductNotFound;
import ma.nemo.assignment.service.ReturnProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/return")
@RequiredArgsConstructor
@Slf4j
public class ReturnProductController {
    private final ReturnProductService returnProductService;
    @PostMapping
    public ResponseEntity<?> addReturnProduct(@Valid @RequestBody ReturnProductDTO returnProductDTO) throws ProductNotFound {
        log.info("Return Product to stock : " + returnProductDTO.getProductCode());
        return new ResponseEntity<>(returnProductService.returnProduct(returnProductDTO), HttpStatus.CREATED);
    }
}
