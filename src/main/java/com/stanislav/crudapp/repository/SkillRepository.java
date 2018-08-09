package main.java.com.stanislav.crudapp.repository;

import main.java.com.stanislav.crudapp.exceptions.EmptyFileException;
import main.java.com.stanislav.crudapp.model.Skill;
import java.util.Collection;
import java.util.List;


public interface SkillRepository extends GenericRepository<Skill, Long> {

    @Override
    void save(Skill skill);

    @Override
    Skill getById(Long id)throws EmptyFileException;

    @Override
    Collection getAll() throws EmptyFileException;

    @Override
    void delete(Skill skill)throws EmptyFileException;

    @Override
    void update(Skill skill)throws EmptyFileException;

}
