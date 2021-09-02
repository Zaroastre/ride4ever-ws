/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.infrastructure.api;

public interface Crud<K, T> {

    /**
     * 
     * @param entity
     * @return
     */
    T create(T entity);

    /**
     * 
     * @param identifier
     * @return
     */
    T read(K identifier);

    /**
     * 
     * @param identifier
     * @param entity
     * @return
     */
    T update(K identifier, T entity);

    /**
     * 
     * @param identifier
     * @return
     */
    T delete(K identifier);
}
