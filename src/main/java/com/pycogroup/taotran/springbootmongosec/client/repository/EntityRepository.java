package com.pycogroup.taotran.springbootmongosec.client.repository;

import com.pycogroup.taotran.springbootmongosec.client.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityRepository<T extends AbstractEntity> extends JpaRepository<T, String> {
}
