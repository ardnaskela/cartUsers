package com.cartdemo.user.util;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Log4j2
public class FieldsComparator {

    public static boolean fieldsAreEqualForLists(List<?> source, List<?> target, String[] fieldsToExclude) {

        // all fields of all elements match flag
        boolean toReturn = true;

        // checking if all fields of same-index elements of both lists are equal
        for (int i = 0; i < source.size(); i++) {
            if (!fieldsAreEqual(source.get(i), target.get(i), fieldsToExclude)) {
                 toReturn = false;
            }
        }
        return toReturn;
    }

    @SneakyThrows
    public static boolean fieldsAreEqual(Object source, Object target, String[] fieldsToExclude) {
        Map<String, String> fieldsSrc = BeanUtils.describe(source);
        Map<String, String> fieldsTrg = BeanUtils.describe(target);

        // excluding fields that don't need to be checked
        if (fieldsToExclude != null) {
            for (String s : fieldsToExclude)
                fieldsSrc.remove(s);
        }

        // all fields match flag
        boolean toReturn=true;

        // checking that fields of source and target objects match and changing toReturn flag to false if at least one pair doesn't
        for(Map.Entry<String, String> entry : fieldsSrc.entrySet()) {
            if (!(fieldsTrg.get(entry.getKey()) == null && entry.getValue() == null)
                    && !fieldsTrg.get(entry.getKey()).equalsIgnoreCase(entry.getValue())) {
                /* logging fields that didn't match in this format:
                   FIELD MISMATCH!
                   Source.key = value
                   Target.key = value */
                log.error(String.format("%n%nFIELD MISMATCH!%n%s.%s = %s%n%s.%s = %s%n", source.getClass().getSimpleName(), entry.getKey(),entry.getValue(), target.getClass().getSimpleName(), entry.getKey(), fieldsTrg.get(entry.getKey())));
                toReturn = false;
                }
            }
        return toReturn;
    }

    public static boolean fieldsAreEqual(Object source, Object target) {
        return fieldsAreEqual(source, target, null);
    }

    public static boolean fieldsAreEqualForLists(List<?> source, List<?> target) {
        return fieldsAreEqualForLists(source, target, null);
    }

    private FieldsComparator() {}
}
