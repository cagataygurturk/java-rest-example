package com.cagataygurturk.storage;


import java.util.Map;

public interface Storage<T extends Storable> {

    T getObjectByPrimaryIndex(long index);

    T saveObject(T objectToSave);

    Map<Long, T> getAllObjects();

    Map<Long, T> getObjectsByCriteria(String id, Object value);
}
