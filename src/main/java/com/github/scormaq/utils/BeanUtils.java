package com.github.scormaq.utils;

import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.EnumUtils.getEnum;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanUtils {

    public static <E extends Enum<E>> Map<E, String> toEnumMap(Map<String, String> map,
        Class<E> enumType) {
        // collect to TreeMap to guarantee map entry ordering same as enum order
        return map.entrySet().stream().collect(toMap(
            e -> getEnum(enumType, e.getKey()),
            Entry::getValue,
            (v1, v2) -> v1,
            TreeMap::new)
        );
    }
}
