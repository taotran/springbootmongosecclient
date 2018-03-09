package com.pycogroup.taotran.client.service;

import com.pycogroup.taotran.client.entity.AbstractEntity;

import java.util.List;

public interface EntityService<T extends AbstractEntity> {

    List<T> findAll();

    T findOne(String id);

    T save(T t);

    void delete(T t);

    void delete(String id);

}
