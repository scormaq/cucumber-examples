package com.github.scormaq.utils;

import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.EnumUtils.getEnum;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Supplier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtilsBean2;

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

    private static NullAwareBeanUtilsBean nullAwareBeanUtilsBean;

    private static NullAwareBeanUtilsBean getNullAwareBeanUtilsBean() {
        if (nullAwareBeanUtilsBean == null) {
            nullAwareBeanUtilsBean = new NullAwareBeanUtilsBean();
        }
        return nullAwareBeanUtilsBean;
    }

    @SneakyThrows
    public static void copyNonNullProperties(Object dest, Object origin) {
        getNullAwareBeanUtilsBean().copyProperties(dest, origin);
    }

    @SneakyThrows
    public static <T> T copyNonNullProperties(Supplier<T> destinationSupplier, Object origin) {
        T result = destinationSupplier.get();
        getNullAwareBeanUtilsBean().copyProperties(result, origin);
        return result;
    }

    private static class NullAwareBeanUtilsBean extends BeanUtilsBean2 {

        @Override
        public void copyProperty(Object bean, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
            if (value == null) {
                return;
            }
            super.copyProperty(bean, name, value);
        }
    }

    /**
     * Nullify all bean properties, except those which are non-null in sample
     *
     * @param beanToNormalize bean which properties should be set to null if they are null in sample bean
     * @param beanWithNonNullProperties sample bean with some non-null values
     * @return beanToNormalize with some properties nullified
     */
    @SneakyThrows
    public static <T> T normalizeNonNullProperties(T beanToNormalize, T beanWithNonNullProperties) {
        new NullifyPropsBean().copyProperties(beanToNormalize, beanWithNonNullProperties);
        return beanToNormalize;

    }

    private static class NullifyPropsBean extends BeanUtilsBean2 {

        @Override
        public void copyProperty(Object destinationBean, String propName, Object sourcePropValue)
            throws IllegalAccessException, InvocationTargetException {
            if (sourcePropValue == null) {
                super.setProperty(destinationBean, propName, null);
            }
        }
    }
}
