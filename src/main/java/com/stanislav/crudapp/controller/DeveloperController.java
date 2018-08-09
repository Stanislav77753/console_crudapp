package main.java.com.stanislav.crudapp.controller;

import main.java.com.stanislav.crudapp.exceptions.DeveloperAlreadyExistException;
import main.java.com.stanislav.crudapp.exceptions.EmptyFileException;
import main.java.com.stanislav.crudapp.model.Account;
import main.java.com.stanislav.crudapp.model.Developer;
import main.java.com.stanislav.crudapp.model.Skill;
import main.java.com.stanislav.crudapp.service.DeveloperService;
import java.util.Set;

public class DeveloperController {
    DeveloperService developerService = new DeveloperService();

    public void addDeveloper(Developer developer) throws DeveloperAlreadyExistException {
        developerService.addDeveloper(developer);
    }

    public void lookAllDevelopers() throws EmptyFileException {
        developerService.getAllDevelopers();
    }

    public void deleteDeveloperById(Long id) throws EmptyFileException {
        developerService.deleteDeveloperById(id);
    }

    public void deleteDeveloperByLogin(String login) throws EmptyFileException {
        developerService.deleteDeveloperByLogin(login);
    }

    public void updateDeveloperById(Long id,String login, String password, String firstName, String lastName, Set<Skill> skills,
                                    Account account ) throws EmptyFileException {
        developerService.updateDeveloperById(new Developer(id, login, password, firstName, lastName, skills, account));
    }
}
