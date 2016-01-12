package com.cagataygurturk.services.transaction;


import com.cagataygurturk.models.Transaction;

public interface TransactionService {
    Transaction getTransactionById(long transactionId) throws TransactionNotFoundException;

    Transaction createNewTransaction(double amount, String type);

    Transaction createNewTransaction(double amount, String type, long parentId);
}
