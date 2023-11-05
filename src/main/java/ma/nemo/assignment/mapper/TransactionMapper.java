package ma.nemo.assignment.mapper;

import ma.nemo.assignment.domain.TransactionHistory;
import ma.nemo.assignment.dto.TransactionHistoryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static TransactionHistoryDTO toDTO(TransactionHistory transactionHistory){
        return modelMapper.map(transactionHistory, TransactionHistoryDTO.class);
    }

    public static TransactionHistory toEntity(TransactionHistoryDTO transactionHistoryDTO){
        return modelMapper.map(transactionHistoryDTO, TransactionHistory.class);
    }

}
