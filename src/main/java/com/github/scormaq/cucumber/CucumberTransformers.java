package com.github.scormaq.cucumber;

import static org.apache.commons.lang3.StringUtils.endsWith;
import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.apache.commons.lang3.StringUtils.strip;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;

@SuppressWarnings("unused")
public class CucumberTransformers {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @DefaultParameterTransformer
    @DefaultDataTableCellTransformer(replaceWithEmptyString = {"[blank]"})
    public Object defaultTransformer(Object fromValue, Type toValueType) {
        return MAPPER.convertValue(fromValue, MAPPER.constructType(toValueType));
    }

    @SneakyThrows
    @DefaultDataTableEntryTransformer(replaceWithEmptyString = {"[blank]"})
    public Object tableTransformer(Map<String, String> table, Type toValueType) {
        String json = JsonUnflattener.unflatten(MAPPER.writeValueAsString(table));
        return transform(table, TypeFactory.rawClass(toValueType));
    }

    public static <T> T transform(Map<String, String> table, Class<T> type) {
        var map = table.entrySet().stream().collect(
            HashMap::new,
            (m, v) -> m.put(v.getKey(), processValue(v.getValue())),
            HashMap::putAll);
        try {
            String json = JsonUnflattener.unflatten(MAPPER.writeValueAsString(map));
            return MAPPER.readerFor(type).readValue(json);
        } catch (IOException e) {
            throw new CucumberFrameworkException(
                "Fail to parse cucumber table into instance of " + type, e);
        }
    }

    /**
     * Apply additional transformation logic for table value
     */
    private static Object processValue(String value) {
        if (startsWith(value, "[") && endsWith(value, "]")) {
            String collectionStr = strip(value, "[]");
            return collectionStr.split(", *");
        }
        return value;
    }
}
