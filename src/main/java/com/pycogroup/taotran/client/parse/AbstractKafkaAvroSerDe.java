package com.pycogroup.taotran.client.parse;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericContainer;
import org.apache.commons.lang.SerializationException;
import org.apache.kafka.common.config.ConfigException;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractKafkaAvroSerDe {

    protected static final byte MAGIC_BYTE = 0x0;
    protected static final int idSize = 4;

    private static final Map<String, Schema> primitiveSchemas;
    protected SchemaRegistryClient schemaRegistry;
    protected SubjectNameStrategy keySubjectNameStrategy = new TopicNameStrategy();
    protected SubjectNameStrategy valueSubjectNameStrategy = new TopicNameStrategy();

    static {
        Schema.Parser parser = new Schema.Parser();
        primitiveSchemas = new HashMap<>();
        primitiveSchemas.put("Null", createPrimitiveSchema(parser, "null"));
        primitiveSchemas.put("Boolean", createPrimitiveSchema(parser, "boolean"));
        primitiveSchemas.put("Integer", createPrimitiveSchema(parser, "int"));
        primitiveSchemas.put("Long", createPrimitiveSchema(parser, "long"));
        primitiveSchemas.put("Float", createPrimitiveSchema(parser, "float"));
        primitiveSchemas.put("Double", createPrimitiveSchema(parser, "double"));
        primitiveSchemas.put("String", createPrimitiveSchema(parser, "string"));
        primitiveSchemas.put("Bytes", createPrimitiveSchema(parser, "bytes"));
    }

    private static Schema createPrimitiveSchema(Schema.Parser parser, String type) {
        String schemaString = String.format("{\"type\" : \"%s\"}", type);
        return parser.parse(schemaString);
    }

    protected static Map<String, Schema> getPrimitiveSchemas() {
        return Collections.unmodifiableMap(primitiveSchemas);
    }

    protected void configureClientProperties(AbstractKafkaAvroSerDeConfig config) {
        try {
            List<String> urls = config.getSchemaRegistryUrls();
            int maxSchemaObject = config.getMaxSchemasPerSubject();
            Map<String, Object> originals = config.originalsWithPrefix("");
            if (null == schemaRegistry) {
                schemaRegistry = new CachedSchemaRegistryClient(urls, maxSchemaObject, originals);
            }
            keySubjectNameStrategy = config.keySubjectNameStrategy();
            valueSubjectNameStrategy = config.valueSubjectNameStrategy();
        } catch (io.confluent.common.config.ConfigException e) {
            throw new ConfigException(e.getMessage());
        }
    }

    /**
     * Get the subject name for the given topic and value type.
     */
    protected String getSubjectName(String topic, boolean isKey, Object value) {
        if (isKey) {
            return keySubjectNameStrategy.getSubjectName(topic, isKey, value);
        } else {
            return valueSubjectNameStrategy.getSubjectName(topic, isKey, value);
        }
    }

    /**
     * Get the subject name used by the old Encoder interface, which relies only on the value type
     * rather than the topic.
     */
    protected String getOldSubjectName(Object value) {
        if (value instanceof GenericContainer) {
            return ((GenericContainer) value).getSchema().getName() + "-value";
        } else {
            throw new SerializationException("Primitive types are not supported yet");
        }
    }

    protected Schema getSchema(Object object) {
        if (object == null) {
            return primitiveSchemas.get("Null");
        } else if (object instanceof Boolean) {
            return primitiveSchemas.get("Boolean");
        } else if (object instanceof Integer) {
            return primitiveSchemas.get("Integer");
        } else if (object instanceof Long) {
            return primitiveSchemas.get("Long");
        } else if (object instanceof Float) {
            return primitiveSchemas.get("Float");
        } else if (object instanceof Double) {
            return primitiveSchemas.get("Double");
        } else if (object instanceof CharSequence) {
            return primitiveSchemas.get("String");
        } else if (object instanceof byte[]) {
            return primitiveSchemas.get("Bytes");
        } else if (object instanceof GenericContainer) {
            return ((GenericContainer) object).getSchema();
        } else {
            throw new IllegalArgumentException(
                    "Unsupported Avro type. Supported types are null, Boolean, Integer, Long, "
                            + "Float, Double, String, byte[] and IndexedRecord");
        }
    }

    public int register(String subject, Schema schema) throws IOException, RestClientException {
        return schemaRegistry.register(subject, schema);
    }

    public Schema getById(int id) throws IOException, RestClientException {
        return schemaRegistry.getById(id);
    }

    public Schema getBySubjectAndId(String subject, int id) throws IOException, RestClientException {
        return schemaRegistry.getBySubjectAndId(subject, id);
    }
}
