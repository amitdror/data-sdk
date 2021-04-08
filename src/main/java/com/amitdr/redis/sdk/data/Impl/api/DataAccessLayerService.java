package com.amitdr.redis.sdk.data.Impl.api;

import com.amitdr.redis.sdk.data.Impl.dao.DataAccessManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataAccessLayerService implements DataAccessLayer {

    private final DataAccessManager nonTransactionalDataAccessManager;
    private final DataAccessLayer transactionalDataAccessManager;
    private DataAccessManager currentManager;

    @Autowired
    public DataAccessLayerService(DataAccessManager nonTransactionalDataAccessManager, DataAccessLayer transactionalDataAccessManager) {
        this.nonTransactionalDataAccessManager = nonTransactionalDataAccessManager;
        this.transactionalDataAccessManager = transactionalDataAccessManager;
        this.currentManager = this.nonTransactionalDataAccessManager;
    }

    @Override
    public void insert(Integer key, String value) {
        currentManager.insert(key, value);
    }

    @Override
    public void update(Integer key, String value) {
        currentManager.update(key, value);
    }

    @Override
    public void upsert(Integer key, String value) {
        currentManager.upsert(key, value);
    }

    @Override
    public void delete(Integer key) {
        currentManager.delete(key);
    }

    @Override
    public String select(Integer key) {
        return currentManager.select(key);
    }

    @Override
    public void beginTransaction() {
        transactionalDataAccessManager.beginTransaction();
        currentManager = nonTransactionalDataAccessManager;
    }

    @Override
    public void commit() {
        transactionalDataAccessManager.commit();
        currentManager = nonTransactionalDataAccessManager;
    }

    @Override
    public void rollback() {
        transactionalDataAccessManager.rollback();
        currentManager = nonTransactionalDataAccessManager;
    }
}
