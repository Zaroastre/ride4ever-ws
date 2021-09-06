package io.nirahtech.ride4ever.motorcycleclub.core;

public interface Crud<I, T> {
    T create(T entity);
    T read(I id);
    T update(I id, T entity);
    T delete(I id);
}
