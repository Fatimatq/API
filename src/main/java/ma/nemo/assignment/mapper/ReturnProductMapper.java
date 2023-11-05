package ma.nemo.assignment.mapper;

import ma.nemo.assignment.domain.ReturnProduct;
import ma.nemo.assignment.dto.ReturnProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReturnProductMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public ReturnProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ReturnProductDTO toDTO(ReturnProduct returnProduct) {
        return modelMapper.map(returnProduct, ReturnProductDTO.class);
    }

    public ReturnProduct toEntity(ReturnProductDTO returnProductDTO) {
        return modelMapper.map(returnProductDTO, ReturnProduct.class);
    }
}
