package main.java.com.stanislav.crudapp.repository;

import main.java.com.stanislav.crudapp.exceptions.EmptyFileException;
import main.java.com.stanislav.crudapp.model.Developer;

import java.util.Collection;


public interface DeveloperRepository extends GenericRepository<Developer, Long> {
    @Override
    void save(Developer developer);

    @Override
    Developer getById(Long id)  throws EmptyFileException;

    @Override
    Collection getAll() throws EmptyFileException;

    @Override
    void delete(Developer developer)throws EmptyFileException;

    @Override
    void update(Developer developer)  throws EmptyFileException;
}
