package com.cagataygurturk.services.transaction;


import com.cagataygurturk.models.Transaction;
import com.cagataygurturk.services.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TransactionServiceImpl implements TransactionService {

    protected TransactionRepository repository;

    @Autowired
    public TransactionServiceImpl(@Qualifier("transaction_repository_inmemory")
                                  TransactionRepository repository) {

        this.repository = repository;

    }

    public Transaction getTransactionById(long transactionId) throws TransactionNotFoundException {

        Transaction transaction = this.repository.getTransactionById(transactionId);

        if (null == transaction) {
            throw new TransactionNotFoundException("Transaction with id " + transactionId + " not found");
        }

        return transaction;
    }

    protected Transaction saveTransactionToRepository(Transaction transaction) {
        return this.repository.saveTransaction(transaction);
    }

    public Transaction createNewTransaction(double amount, String type) {
        Transaction transaction = new Transaction(amount, type);
        return this.saveTransactionToRepository(transaction);
    }

    public Transaction createNewTransaction(double amount, String type, long parentId) throws TransactionNotFoundException {
        /**
         * Get parent transaction
         */
        Transaction parentTransaction = this.getTransactionById(parentId);
        Transaction transaction = new Transaction(amount, type, parentTransaction);
        return this.saveTransactionToRepository(transaction);
    }
}
