package fr.epita.advjava.services;

import fr.epita.advjava.datamodel.Country;

import java.util.List;

public interface GenericDAO<T> {

    void create(T instance);
    void update(T instance);
    void delete(T instance);
    List<T> search(T instance);
    T getById(T instance);
}
