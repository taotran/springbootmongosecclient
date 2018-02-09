package com.pycogroup.taotran.springbootmongosec.client.service;

import com.pycogroup.taotran.springbootmongosec.client.entity.AbstractEntity;

import java.util.List;

public interface EntityService<T extends AbstractEntity> {

    List<T> findAll();

    T findOne(String id);

    T save(T t);

    void delete(T t);

    void delete(String id);

}
