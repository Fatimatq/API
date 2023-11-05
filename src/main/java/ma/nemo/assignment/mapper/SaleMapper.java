package ma.nemo.assignment.mapper;

import ma.nemo.assignment.domain.Sale;
import ma.nemo.assignment.dto.SaleDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public SaleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SaleDTO toDTO(Sale sale) {
        return modelMapper.map(sale, SaleDTO.class);
    }

    public Sale toEntity(SaleDTO saleDTO) {
        return modelMapper.map(saleDTO, Sale.class);
    }

}
