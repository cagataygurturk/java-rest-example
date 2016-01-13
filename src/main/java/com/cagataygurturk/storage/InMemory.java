package com.cagataygurturk.storage;


import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

@Component("storage_inmemory")
public class InMemory<T extends Storable> implements Storage<T> {

    protected TreeMap<Long, T> objectList = new TreeMap<>();

    public T getObjectByPrimaryIndex(long index) {
        return objectList.get(index);
    }

    public T saveObject(T objectToSave) {
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

    public Map<Long, T> getAllObjects() {
        return this.objectList;
    }

    public Map<Long, T> getObjectsByCriteria(String fieldName, Object value) {

        TreeMap<Long, T> results = new TreeMap<>();

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
