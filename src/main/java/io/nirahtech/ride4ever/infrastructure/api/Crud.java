package io.nirahtech.ride4ever.infrastructure.api;

public interface Crud<K, T> {
    T create(T entity);
    T read(K identifier);
    T update(K identifier, T entity);
    T delete(K identifier);
}
