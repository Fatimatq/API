package ma.nemo.assignment.mapper;

import ma.nemo.assignment.domain.Supply;
import ma.nemo.assignment.dto.SupplyDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplyMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public SupplyMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SupplyDTO toDTO(Supply supply) {
        return modelMapper.map(supply, SupplyDTO.class);
    }

    public Supply toEntity(SupplyDTO supplyDTO) {
        return modelMapper.map(supplyDTO, Supply.class);
    }

}
