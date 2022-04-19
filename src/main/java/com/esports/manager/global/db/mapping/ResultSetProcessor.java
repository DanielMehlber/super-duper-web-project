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

    public static <T> List<T> convert(final Class<T> targetClass, ResultSet resultSet) throws InternalErrorException {
        // collect all fields with this annotation inside target class
        final List<Field> fields = Arrays.stream(targetClass.getDeclaredFields())
                .filter(x -> x.isAnnotationPresent(ResultSetMapping.class))
                .collect(Collectors.toList());

        final List<T> entities = new LinkedList<T>();

        try {
            while(resultSet.next()) {
                T entity = targetClass.getDeclaredConstructor().newInstance();
                for (Field field : fields) {
                    String columnName = field.getAnnotation(ResultSetMapping.class).value();
                    // will throw SQLException if the column does not exist
                    int index = resultSet.findColumn(columnName);

                    /*
                     * check for type of field and set value accordingly (if supported)
                     */
                    Class<?> type = field.getType();
                    if(type == Integer.class) {
                        field.setInt(entity, resultSet.getInt(index));
                    } else if (type == String.class) {
                        field.set(entity, resultSet.getString(index));
                    } else if (type == Long.class) {
                        field.setLong(entity, resultSet.getLong(index));
                    } else if (type == Float.class) {
                        field.setFloat(entity, resultSet.getFloat(index));
                    } else if (type == Double.class) {
                        field.setDouble(entity, resultSet.getDouble(index));
                    } else if (type == Boolean.class) {
                        field.setBoolean(entity, resultSet.getBoolean(index));
                    } else if (type == Date.class) {
                        field.set(entity, resultSet.getDate(index));
                    } else {
                        throw new IllegalAccessException(String.format("field '%s' with type %s of class %s is not supported", field.getName(), type.getName(), targetClass.getName()));
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
