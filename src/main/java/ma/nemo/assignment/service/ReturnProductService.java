package ma.nemo.assignment.service;

import ma.nemo.assignment.dto.ReturnProductDTO;
import ma.nemo.assignment.exceptions.ProductNotFound;
import org.springframework.stereotype.Service;

@Service
public interface ReturnProductService {
     ReturnProductDTO returnProduct(ReturnProductDTO returnProductDTO) throws ProductNotFound ;

}
