package com.pycogroup.taotran.client.config.kafka;

import com.pycogroup.taotran.client.parse.deserializer.AvroDeserializer;
import com.pycogroup.taotran.springbootmongosec.avroentity.Task;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificRecord;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableKafka
public class KafkaReceiverConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReceiverConfig.class);

    //    @Value("${kafka.bootstrap-servers}")
//    private String bootstrapServers;
    @Autowired
    Environment env;



    @Autowired
    private AvroDeserializer<Task> avroDeserializer;

//    @Autowired
//    private <T extends SpecificRecordBase> AbstractConsumerFactory<String, T>

    @Bean
    public Map<String, Object> receiverConfigs() {
        final Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("bootstrap-servers"));
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AvroDeserializer.class);

        props.put(ConsumerConfig.GROUP_ID_CONFIG, "avro");

        return props;
    }


    @Bean
    public ConsumerFactory<String, Task> receiverFactory() {
        return new DefaultKafkaConsumerFactory<>(receiverConfigs(), new StringDeserializer(), new AvroDeserializer<>(Task.class));
    }


    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, SpecificRecordBase>> kafkaListenerContainerFactory() {
        final ConcurrentKafkaListenerContainerFactory<String, SpecificRecordBase> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(receiverFactory());
        return factory;
    }

}
