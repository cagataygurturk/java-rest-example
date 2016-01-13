package com.cagataygurturk.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionTest {

    /**
     * Extremely small number for double-double comparison
     */
    protected static final double DELTA = 1e-5;

    protected Transaction getTransactionObject() {
        return new Transaction(20.0, "car");
    }


    @Test
    public void testSetTransactionId() throws Exception {
        long newId = 30;
        Transaction transaction = getTransactionObject();
        transaction.setId(newId);
        assertEquals(newId, transaction.getId());
    }

    @Test
    public void testGetAmount() throws Exception {
        assertEquals(20.0, getTransactionObject().getAmount(), DELTA);
    }

    @Test
    public void testSetAmount() throws Exception {
        double newAmount = 30.0;
        Transaction transaction = getTransactionObject();
        transaction.setAmount(newAmount);
        assertEquals(newAmount, transaction.getAmount(), DELTA);

    }

    @Test
    public void testGetType() throws Exception {
        assertEquals("car", getTransactionObject().getType());
    }

    @Test
    public void testSetType() throws Exception {
        String newType = "food";
        Transaction transaction = getTransactionObject();
        transaction.setType(newType);
        assertEquals(newType, transaction.getType());
    }

    @Test
    public void testGetParentId() throws Exception {
        Transaction transaction = getTransactionObject();
        assertNull(transaction.getParentId());
        Transaction parentTransaction = new Transaction(20.0, "bus");
        parentTransaction.setId(2);
        transaction.setParent(parentTransaction);
        assertEquals(2, transaction.getParentId().longValue());
    }


}