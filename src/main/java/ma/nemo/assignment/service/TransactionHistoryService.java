package ma.nemo.assignment.service;

import ma.nemo.assignment.dto.TransactionHistoryDTO;
import org.springframework.stereotype.Service;

@Service
public interface TransactionHistoryService {
    TransactionHistoryDTO addTransaction(TransactionHistoryDTO transactionHistoryDTO);
}
