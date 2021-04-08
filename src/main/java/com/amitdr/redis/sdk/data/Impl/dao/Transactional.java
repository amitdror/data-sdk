package com.amitdr.redis.sdk.data.Impl.dao;

public interface Transactional {

    void beginTransaction();

    void commit();

    void rollback();
}
