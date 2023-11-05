package ma.nemo.assignment.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nemo.assignment.dto.SupplyDTO;
import ma.nemo.assignment.exceptions.ProductNotFound;
import ma.nemo.assignment.service.SupplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("supply")
@RequiredArgsConstructor
@Slf4j
public class SupplyController {
    private final SupplyService supplyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplyDTO addProductToInventory(@Valid @RequestBody SupplyDTO supplyDTO) throws ProductNotFound{
        log.info("Create a new Supply Transaction : {}", supplyDTO);
        return supplyService.addProductToInventory(supplyDTO);
    }
}
