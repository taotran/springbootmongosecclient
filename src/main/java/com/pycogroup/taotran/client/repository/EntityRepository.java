package com.pycogroup.taotran.client.repository;

import com.pycogroup.taotran.client.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityRepository<T extends AbstractEntity> extends JpaRepository<T, String> {
}
