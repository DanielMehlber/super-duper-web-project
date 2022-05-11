package com.esports.manager.global.db.mapping;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation can be used to map a field to a column of a {@link java.sql.ResultSet} and therefor allows automatic
 * conversion from {@link java.sql.ResultSet} to a List of objects using the {@link ResultSetProcessor}.
 *
 * @see ResultSetProcessor
 * @author Daniel Mehlber
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResultSetMapping {

    /**
     * Name of column in result set that will be mapped to the annotated field
     */
    String value();
}
