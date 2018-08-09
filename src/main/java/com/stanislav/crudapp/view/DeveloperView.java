package main.java.com.stanislav.crudapp.view;

import main.java.com.stanislav.crudapp.controller.AccountController;
import main.java.com.stanislav.crudapp.controller.DeveloperController;
import main.java.com.stanislav.crudapp.controller.HelpController;
import main.java.com.stanislav.crudapp.controller.SkillController;
import main.java.com.stanislav.crudapp.exceptions.CloseOperationException;
import main.java.com.stanislav.crudapp.exceptions.DeveloperAlreadyExistException;
import main.java.com.stanislav.crudapp.exceptions.EmptyFileException;
import main.java.com.stanislav.crudapp.model.Account;
import main.java.com.stanislav.crudapp.model.Developer;
import main.java.com.stanislav.crudapp.model.Skill;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DeveloperView {
    public void developerMenu() {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        DeveloperController developerController = new DeveloperController();
        while (!command.equals("close")) {
            System.out.println("You are in developer menu, to get help enter command \"help\":");
            System.out.println("Enter command:");
            command = scanner.nextLine().toLowerCase().trim();
            switch (command) {
                case "add new developer":
                    try {
                        String login = ConsoleHelper.getStringFromConsole("login");
                        String password = ConsoleHelper.getStringFromConsole("password");
                        String firstName = ConsoleHelper.getStringFromConsole("firstName");
                        String lastName = ConsoleHelper.getStringFromConsole("lastName");
                        developerController.addDeveloper(new Developer(0L, login, password, firstName, lastName,
                                getSkill(), getAccount()));
                    } catch (DeveloperAlreadyExistException developerAlreadyExistException) {
                        System.out.println(developerAlreadyExistException.getMessage());
                    } catch (CloseOperationException closeOperationException) {
                        System.out.println(closeOperationException.getMessage());
                    }
                    break;
                case "look all developers":
                    try {
                        developerController.lookAllDevelopers();
                    } catch (EmptyFileException emptyFileException) {
                        System.out.println(emptyFileException.getMessage());
                    }
                    break;
                case "delete developer by id":
                    try {
                        developerController.deleteDeveloperById(ConsoleHelper.getIdFromConsole("developer"));
                    } catch (EmptyFileException emptyFileException) {
                        System.out.println(emptyFileException.getMessage());
                    } catch (CloseOperationException closeOperationException) {
                        System.out.println(closeOperationException.getMessage());
                    }
                    break;
                case "delete developer by login":
                    try {
                        developerController.deleteDeveloperByLogin(ConsoleHelper.getStringFromConsole("login"));
                    } catch (EmptyFileException emptyFileException) {
                        System.out.println(emptyFileException.getMessage());
                    } catch (CloseOperationException closeOperationException) {
                        System.out.println(closeOperationException.getMessage());
                    }
                    break;
                case "update developer by id":
                    try {
                        Long id = ConsoleHelper.getIdFromConsole("developer");
                        String login = ConsoleHelper.getStringFromConsole("login");
                        String password = ConsoleHelper.getStringFromConsole("password");
                        String firstName = ConsoleHelper.getStringFromConsole("firstName");
                        String lastName = ConsoleHelper.getStringFromConsole("lastName");
                        developerController.updateDeveloperById(id,login,password,firstName,lastName,getSkill(),getAccount());
                    } catch (EmptyFileException emptyFileException) {
                        System.out.println(emptyFileException.getMessage());
                    }catch (CloseOperationException closeOperationException) {
                        System.out.println(closeOperationException.getMessage());
                    }
                    break;
                case "help":
                    new HelpController().getHelp("DEVELOPER");
                    break;
            }
        }
    }

    private Set<Skill> getSkill() throws CloseOperationException {
        SkillController skillController = new SkillController();
        Set<Skill> skills = new HashSet<>();
        boolean flagSkill = true;
        do {
            try {
                skills.add(skillController.getSkillById(ConsoleHelper.getIdFromConsole("skill")));
            } catch (EmptyFileException e) {
                e.printStackTrace();
            } catch (CloseOperationException closeOperationException) {
                System.out.println(closeOperationException.getMessage());
                flagSkill = false;
            }
        } while (flagSkill);
        if (skills.isEmpty()) {
            throw new CloseOperationException("You didn't enter anyone skill");
        }
        return skills;
    }

    private Account getAccount() throws CloseOperationException {
        AccountController accountController = new AccountController();
        Account account = null;
        try {
            account = accountController.getAccountById(ConsoleHelper.getIdFromConsole("account"));
        } catch (EmptyFileException emptyFileException) {
            System.out.println(emptyFileException.getMessage());
        }
        return account;
    }

}
