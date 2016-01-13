package com.cagataygurturk.services.transaction;

import com.cagataygurturk.models.Transaction;
import com.cagataygurturk.services.transaction.repository.TransactionRepository;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;


public class TransactionServiceImplTest {

    /**
     * Extremely small number for double-double comparison
     */
    protected static final double DELTA = 1e-5;


    protected TransactionRepository getMockRepository() {
        Transaction mockTransaction = new Transaction(20.0, "cars");
        mockTransaction.setId(1);
        mockTransaction.setParent(mockTransaction);

        TransactionRepository mockRepository = createNiceMock(TransactionRepository.class);
        expect(mockRepository.getTransactionById(1))
                .andReturn(mockTransaction)
                .anyTimes();

        expect(mockRepository.saveTransaction(anyObject()))
                .andReturn(mockTransaction)
                .anyTimes();

        replay(mockRepository);
        return mockRepository;
    }


    @Test(expected = TransactionNotFoundException.class)
    public void testGetNotFoundTransaction() throws Exception {
        TransactionService transactionService = new TransactionServiceImpl(getMockRepository());
        transactionService.getTransactionById(12);
    }

    @Test
    public void testGetTransactionById() throws Exception {
        TransactionService transactionService = new TransactionServiceImpl(getMockRepository());
        Transaction transaction = transactionService.getTransactionById(1);
        assertNotNull(transaction);
        assertEquals(1, transaction.getId());
    }

    @Test
    public void testCreateNewTransaction() throws Exception {
        TransactionService transactionService = new TransactionServiceImpl(getMockRepository());
        Transaction transaction = transactionService.createNewTransaction(20.0, "cars");
        assertNotNull(transaction);
        assertEquals(20.0, transaction.getAmount(), DELTA);
        assertEquals("cars", transaction.getType());
    }

    @Test
    public void testCreateNewTransactionWithParent() throws Exception {
        TransactionService transactionService = new TransactionServiceImpl(getMockRepository());
        Transaction transaction = transactionService.createNewTransaction(20.0, "cars", 1);
        assertNotNull(transaction);
        assertEquals(20.0, transaction.getAmount(), DELTA);
        assertEquals("cars", transaction.getType());
        assertEquals(1, (long) transaction.getParentId());
    }

}