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
                System.out.println(stringDeveloper);
                String[] strArray = stringDeveloper.trim().split(";");
                if(developer.getLogin().equals(strArray[1])){
                    throw new DeveloperAlreadyExistException("This developer already exist");
                }
                id = new Long(strArray[0]) + 1L;
            }
        } catch (EmptyFileException emptyFileException) {
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
                System.out.print(skills.getId()+", ");
            }
            System.out.println();
            System.out.println("Account - " + developers.getAccount().getId());
        }
    }

    public void deleteDeveloper(Developer developer) throws EmptyFileException {
        javaIODeveloperRepository.delete(developer);
    }

    public void updateDeveloperById(Developer developer) throws EmptyFileException {
        javaIODeveloperRepository.update(developer);
    }


}
