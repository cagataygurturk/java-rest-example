package com.cagataygurturk.storage;


import java.util.Map;

public interface Storage {

    Storable getObjectByPrimaryIndex(long index);

    Storable saveObject(Storable objectToSave);

    Map<Long, Storable> getAllObjects();

    Map<Long, Storable> getObjectsByCriteria(String id, Object value);
}
