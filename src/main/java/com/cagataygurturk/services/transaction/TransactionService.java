package com.cagataygurturk.services.transaction;


import com.cagataygurturk.models.Sum;
import com.cagataygurturk.models.Transaction;

import java.util.ArrayList;

public interface TransactionService {
    Transaction getTransactionById(long transactionId) throws TransactionNotFoundException;

    Transaction createNewTransaction(double amount, String type);

    Transaction createNewTransaction(double amount, String type, long parentId);

    Sum calculateSum(Transaction transaction);

    ArrayList<Long> getTransactionsByType(String type);
}
