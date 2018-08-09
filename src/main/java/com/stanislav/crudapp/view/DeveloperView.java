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

import java.util.*;

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
                        developerController.deleteDeveloper(new Developer(ConsoleHelper.getIdFromConsole("developer"),
                                null, null, null, null, null, null));
                    } catch (EmptyFileException emptyFileException) {
                        System.out.println(emptyFileException.getMessage());
                    } catch (CloseOperationException closeOperationException) {
                        System.out.println(closeOperationException.getMessage());
                    }
                    break;
                case "delete developer by login":
                    try {
                        developerController.deleteDeveloper(new Developer(null,
                                ConsoleHelper.getStringFromConsole("login"), null, null,
                                null, null, null ));
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
                        developerController.updateDeveloperById(new Developer(id, login, password, firstName,
                                lastName,getSkill(),getAccount()));
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
        Set<Long> setId = new TreeSet<>();
        Set<Skill> skills = new HashSet<>();
        boolean flagSkill = true;
        try {
            List<Skill> allSkills = skillController.getAllSkills();
            do{
                try {
                    Long id = ConsoleHelper.getIdFromConsole("skill");
                    boolean flagId = true;
                    for (Skill skillFromList : allSkills) {
                        if (skillFromList.getId().equals(id)) {
                            setId.add(id);
                            flagId = false;
                            break;
                        }
                    }
                    if(flagId){
                        System.out.println("skill with id - " + id + " is not exist");
                    }
                }catch (CloseOperationException closeOperationException) {
                    System.out.println(closeOperationException.getMessage());
                    flagSkill = false;
                }
            }while (flagSkill);
            for(Long id: setId){
                skills.add(skillController.getSkillById(id));
            }
        } catch (EmptyFileException e) {
            System.out.println(e.getMessage());
        }
        if (skills.isEmpty()) {
            throw new CloseOperationException("You didn't enter anyone skill");
        }
        return skills;
    }

    private Account getAccount() throws CloseOperationException {
        AccountController accountController = new AccountController();
        Account account = null;
        boolean flagAccount = true;
        try {
            List<Account> allAccounts = accountController.getAllAccounts();
            do{
                Long id = ConsoleHelper.getIdFromConsole("account");
                for(Account accountFromList: allAccounts) {
                    if (accountFromList.getId().equals(id)) {
                        account = accountController.getAccountById(id);
                        flagAccount = false;
                        break;
                    }
                }
                if(flagAccount){
                    System.out.println("account with id - " + id + " is not exist");
                }
            }while (flagAccount);
        } catch (EmptyFileException emptyFileException) {
            System.out.println(emptyFileException.getMessage());
        }
        return account;
    }

}
