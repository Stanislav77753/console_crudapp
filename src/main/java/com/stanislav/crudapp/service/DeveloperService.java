package main.java.com.stanislav.crudapp.service;

import main.java.com.stanislav.crudapp.exceptions.DeveloperAlreadyExistException;
import main.java.com.stanislav.crudapp.exceptions.EmptyFileException;
import main.java.com.stanislav.crudapp.model.Account;
import main.java.com.stanislav.crudapp.model.Developer;
import main.java.com.stanislav.crudapp.model.Skill;
import main.java.com.stanislav.crudapp.repository.io.JavaIODeveloperRepositoryImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeveloperService {
    JavaIODeveloperRepositoryImpl javaIODeveloperRepository = new JavaIODeveloperRepositoryImpl();

    public void addDeveloper(Developer developer) throws DeveloperAlreadyExistException {
        Long id = 1L;
        try {
            List<String> allDeveloperFromFile = javaIODeveloperRepository.getDeveloperListString();
            for(String stringDeveloper: allDeveloperFromFile){
                String[] strArray = stringDeveloper.trim().split(";");
                if(developer.getLogin().equals(strArray[1])){
                    throw new DeveloperAlreadyExistException("This developer already exist");
                }
                id++;
            }
        } catch (EmptyFileException emptyFileExecption) {
        }
        developer.setId(id);
        javaIODeveloperRepository.save(developer);
    }

    public void getAllDevelopers() throws EmptyFileException {
        List<Developer> allDeveloperFromFile = javaIODeveloperRepository.getAll();
        for(Developer developers: allDeveloperFromFile){
            System.out.println("Id - " + developers.getId());
            System.out.println("Login - " + developers.getLogin());
            System.out.println("Password - " + developers.getPassword());
            System.out.println("FirstName - " + developers.getFirstName());
            System.out.println("LastName - " + developers.getLastName());
            System.out.print("Skills - ");
            Set<Skill> skill = developers.getSkills();
            for(Skill skills: skill){
                System.out.print(skills.getName()+", ");
            }
            System.out.println();
            System.out.println("Account - " + developers.getAccount().getDeveloperData());
        }
    }

    public void deleteDeveloperById(Long id) throws EmptyFileException {
        javaIODeveloperRepository.delete(new Developer(id, "", "", "", "", new HashSet<>(),
                new Account(null, null)));
    }

    public void deleteDeveloperByLogin(String login) throws EmptyFileException {
        javaIODeveloperRepository.delete(new Developer(null, login, "", "", "", new HashSet<>(),
                new Account(null, null)));
    }

    public void updateDeveloperById(Developer developer) throws EmptyFileException {
        javaIODeveloperRepository.update(developer);
    }

}
