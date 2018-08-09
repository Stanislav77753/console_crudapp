package main.java.com.stanislav.crudapp.repository;


import main.java.com.stanislav.crudapp.exceptions.EmptyFileException;

import java.util.Collection;

public interface GenericRepository<T, ID> {
    void save(T t);
    T getById(ID id)throws EmptyFileException;
    Collection getAll() throws EmptyFileException;
    void delete(T t)throws EmptyFileException;
    void update(T t)throws EmptyFileException;
}
