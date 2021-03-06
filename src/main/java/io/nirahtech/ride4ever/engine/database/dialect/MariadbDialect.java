/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.engine.database.dialect;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * Class that represents a Mariadb Dialect for JPA/Hibernate transactions.
 *
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
public class MariadbDialect extends MySQL5Dialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
