package com.esports.manager.global.db.mapping;

import com.esports.manager.global.exceptions.InternalErrorException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class can be used to automatically convert {@link ResultSet} into an object using the {@link ResultSetMapping}
 * annotation.
 *
 * @see ResultSetMapping
 * @author Daniel Mehlber
 */
public class ResultSetProcessor {

    /**
     * Convert an instance of {@link ResultSet} to a List of objects using the {@link ResultSetMapping} annotation.
     *
     * @param targetClass class in which the resultSet will be converted
     * @param resultSet resultSet that must contain all columns that are required by annotations
     * @return list of targetClass instances
     * @param <T> the expected type of list
     * @throws InternalErrorException conversion error, maybe required columns are missing in the resultSet.
     * @author Daniel Mehlber
     * @see ResultSetMapping
     */
    public static <T> List<T> convert(final Class<T> targetClass, ResultSet resultSet) throws InternalErrorException {
        // collect all fields with this annotation inside target class
        final List<Field> fields = Arrays.stream(targetClass.getDeclaredFields())
                .filter(x -> x.isAnnotationPresent(ResultSetMapping.class))
                .collect(Collectors.toList());

        final List<T> entities = new LinkedList<>();

        try {
            // iterate over rows of ResultSet
            while(resultSet.next()) {
                // create new entity of target class with default constructor
                targetClass.getDeclaredConstructor().setAccessible(true);
                T entity = targetClass.getDeclaredConstructor().newInstance();
                for (Field field : fields) {
                    // get required column name in result set from annotation
                    String columnName = field.getAnnotation(ResultSetMapping.class).value();
                    // will throw SQLException if the column does not exist
                    int index = resultSet.findColumn(columnName);

                    /*
                     * check for type of field and set value accordingly (if supported).
                     * If the annotations' field type is not supported an error is thrown.
                     */
                    Class<?> type = field.getType();
                    if(!field.trySetAccessible()) throw new InternalErrorException("cannot make field accessible");
                    if(type == Integer.class) {
                        field.set(entity, resultSet.getInt(index));
                    } else if (type == String.class) {
                        field.set(entity, resultSet.getString(index));
                    } else if (type == Long.class) {
                        field.set(entity, resultSet.getLong(index));
                    } else if (type == Float.class) {
                        field.set(entity, resultSet.getFloat(index));
                    } else if (type == Double.class) {
                        field.set(entity, resultSet.getDouble(index));
                    } else if (type == Boolean.class) {
                        field.set(entity, resultSet.getBoolean(index));
                    } else if (type == Date.class) {
                        field.set(entity, resultSet.getTimestamp(index));
                    } else {
                        throw new IllegalAccessException(String.format("field '%s' of type %s of class %s is not supported", field.getName(), type.getName(), targetClass.getName()));
                    }
                }

                entities.add(entity);
            }
        } catch (RuntimeException | SQLException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            throw new InternalErrorException("cannot convert ResultSet to instance of " + targetClass.getName(), e);
        }

        return entities;
    }

}
