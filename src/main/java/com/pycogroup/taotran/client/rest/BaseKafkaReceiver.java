package com.pycogroup.taotran.client.rest;

import com.pycogroup.taotran.client.entity.AbstractEntity;
import com.pycogroup.taotran.client.service.EntityService;
import org.apache.avro.specific.SpecificRecordBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

public abstract class BaseKafkaReceiver<T extends AbstractEntity, S extends SpecificRecordBase> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseKafkaReceiver.class);

    @Autowired
    protected EntityService<T> entityService;

    protected CountDownLatch latch = new CountDownLatch(1);

    public void onReceive(S receivedObj) {

        LOGGER.debug("Received '{}'", receivedObj.toString());

        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
