package ma.nemo.assignment.serviceImplementation;

import lombok.RequiredArgsConstructor;
import ma.nemo.assignment.domain.TransactionHistory;
import ma.nemo.assignment.dto.TransactionHistoryDTO;
import ma.nemo.assignment.mapper.TransactionMapper;
import ma.nemo.assignment.repository.TransactionHistoryRepository;
import ma.nemo.assignment.service.TransactionHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionHistoryService {
    private final TransactionMapper transactionMapper;
    private final TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public TransactionHistoryDTO addTransaction(TransactionHistoryDTO transactionHistoryDTO) {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setTransactionDate(LocalDateTime.now());
        transactionHistory.setTransactionType(transactionHistoryDTO.getTransactionType());
        transactionHistory.setUser(transactionHistoryDTO.getUser());
        transactionHistory.setProduct(transactionHistoryDTO.getProduct());
        transactionHistory.setQuantity(transactionHistoryDTO.getQuantity());

        transactionHistoryRepository.save(transactionHistory);


        return transactionMapper.toDTO(transactionHistory);


    }
}
