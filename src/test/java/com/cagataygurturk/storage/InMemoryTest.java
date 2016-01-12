package com.cagataygurturk.storage;

import com.cagataygurturk.models.Transaction;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;


public class InMemoryTest {


    private class CustomObject implements Storable {
        public long id;
        public String value;

        public CustomObject(String value) {
            this.value = value;
        }

        public long getGeneratedId() {
            return id;
        }

        public void setGeneratedId(long id) {
            this.id = id;
        }
    }


    @Test
    public void testSaveAndGetObject() throws Exception {
        InMemory<CustomObject> database = new InMemory<>();
        CustomObject object = new CustomObject("testValue");
        database.saveObject(object);
        CustomObject objectFromDatabase = database.getObjectByPrimaryIndex(1);
        assertNotNull(objectFromDatabase);
        assertEquals(1, objectFromDatabase.getGeneratedId());
    }

    /**
     * Tests if saved objects are getting auto-increment ID values
     *
     * @throws Exception
     */
    @Test
    public void testSetGeneratedIds() throws Exception {
        InMemory<CustomObject> database = new InMemory<>();

        for (long i = 1; i < 10; i++) {
            CustomObject object = new CustomObject("testValue" + i);
            database.saveObject(object);
        }

        for (long i = 1; i < 10; i++) {
            CustomObject objectFromDatabase = database.getObjectByPrimaryIndex(i);
            assertEquals(i, objectFromDatabase.getGeneratedId());
            assertEquals("testValue" + i, objectFromDatabase.value);
        }
    }


    @Test
    public void testGetAllObjects() throws Exception {
        InMemory<CustomObject> database = new InMemory<>();

        for (long i = 1; i <= 10; i++) {
            CustomObject object = new CustomObject("testValue" + i);
            database.saveObject(object);
        }

        Map<Long, CustomObject> allItems = database.getAllObjects();
        assertEquals(10, allItems.size());

        for (long i = 1; i <= 10; i++) {
            assertThat(allItems.get(i), instanceOf(Storable.class));
        }
    }

    @Test
    public void testGetObjectsByCriteria() throws Exception {
        InMemory<CustomObject> database = new InMemory<>();

        for (long i = 1; i <= 10; i++) {
            CustomObject object = new CustomObject("testValue" + i);
            database.saveObject(object);
        }

        Map<Long, CustomObject> searchResults = database.getObjectsByCriteria("value", "testValue1");
        assertEquals(1, searchResults.size());
    }
}