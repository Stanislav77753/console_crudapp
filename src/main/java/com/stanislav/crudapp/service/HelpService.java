package main.java.com.stanislav.crudapp.service;

import main.java.com.stanislav.crudapp.repository.io.JavaIOHelpRepositoryImpl;

import java.util.List;

public class HelpService {
    JavaIOHelpRepositoryImpl javaIOHelpRepository = new JavaIOHelpRepositoryImpl();

    public void getHelp(String chapter){
        List<String> helpList = javaIOHelpRepository.readData(chapter);
        for(String helpString: helpList){
            System.out.println(helpString);
        }
    }
}
