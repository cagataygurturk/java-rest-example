package com.cagataygurturk.storage;


import java.lang.reflect.Field;
import java.util.*;

public class InMemory implements Storage {

    protected TreeMap<Long, Storable> objectList = new TreeMap<>();

    public Storable getObjectByPrimaryIndex(long index) {
        return objectList.get(index);
    }

    public Storable saveObject(Storable objectToSave) {
        long lastObjectId;
        try {
            lastObjectId = objectList.lastKey();
        } catch (NoSuchElementException e) {
            /**
             * Database is empty
             */
            lastObjectId = 0;
        }
        long newObjectId = ++lastObjectId;
        objectToSave.setGeneratedId(newObjectId);
        objectList.put(newObjectId, objectToSave);
        return objectToSave;
    }

    public Map<Long, Storable> getAllObjects() {
        return this.objectList;
    }

    public Map<Long, Storable> getObjectsByCriteria(String fieldName, Object value) {

        TreeMap<Long, Storable> results = new TreeMap<>();

        objectList.forEach((key, object) -> {
            try {
                Field f = object.getClass().getDeclaredField(fieldName);
                f.setAccessible(true);
                Object fieldValue = f.get(object);
                if (fieldValue.equals(value)) {
                    results.put(key, object);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return;
            }
        });

        return results;
    }

}
