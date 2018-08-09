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

    public void deleteDeveloper(Developer developer) throws EmptyFileException {
        developerService.deleteDeveloper(developer);
    }


    public void updateDeveloperById(Developer developer ) throws EmptyFileException {
        developerService.updateDeveloperById(developer);
    }
}
