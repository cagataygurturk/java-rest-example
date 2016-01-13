package com.cagataygurturk.services.transaction.repository;


import com.cagataygurturk.models.Transaction;

import java.util.Map;

abstract public class TransactionRepository {

    /**
     * Data Access Objects are chainable. It allows to attach easily another Data Access Layer in front of another one
     * <p>
     * In a most generic implementation, a cache layer extending TransactionRepository
     */
    protected TransactionRepository fallbackRepository;

    public abstract Transaction getTransactionById(long transactionId);

    public abstract Transaction saveTransaction(Transaction transaction);

    public abstract Map<Long, Transaction> getAllTransactions();

    public abstract Map<Long, Transaction> getTransactionByCriteria(String key, Object value);

    public void setFallbackRepository(TransactionRepository fallbackRepository) {
        this.fallbackRepository = fallbackRepository;
    }
}
