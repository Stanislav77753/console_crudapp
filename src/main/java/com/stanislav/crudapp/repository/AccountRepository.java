package main.java.com.stanislav.crudapp.repository;

import main.java.com.stanislav.crudapp.model.Account;

import java.util.Collection;


public interface AccountRepository extends GenericRepository<Account, Long> {
    @Override
    void save(Account account);

    @Override
    Account getById(Long id);

    @Override
    Collection getAll();

    @Override
    void delete(Account account);

    @Override
    void update(Account account);
}
