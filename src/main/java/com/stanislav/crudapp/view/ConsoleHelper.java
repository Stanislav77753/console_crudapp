package main.java.com.stanislav.crudapp.view;
import main.java.com.stanislav.crudapp.exceptions.CloseOperationException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleHelper {
    static Scanner scanner = new Scanner(System.in);
    private void mainMenu(){
        String command = "";
        SkillView skillView = new SkillView();
        AccountView accountView = new AccountView();
        DeveloperView developerView = new DeveloperView();
        while (!command.equals("close programm")){
            System.out.println("You are in main menu");
            System.out.println("You need choose entity to work : Skill,Developer or Account");
            System.out.println("Enter your choose:");
            command = scanner.nextLine().toLowerCase().trim();
            switch(command){
                case "skill":
                    skillView.skillMenu();
                    break;
                case "developer":
                    developerView.developerMenu();
                    break;
                case "account":
                    accountView.accountMenu();
                    break;
                case "close":
                    break;
            }
        }
    }

    public static String getStringFromConsole(String var) throws CloseOperationException {
        System.out.println("Enter " + var + ". For to cancel operation enter command - \"cancel\"");
        String returnString;
        do{
            returnString = scanner.nextLine().trim();
            if(returnString.length() < 1){
                System.out.println("You enter empty string");
            }
            else{
                if(returnString.equals("cancel")){
                    throw new CloseOperationException("Operation interrupted");
                }
                else{
                    return returnString;
                }
            }
        }
        while (returnString.length() < 1);
        return null;
    }

    public static Long getIdFromConsole(String var) throws CloseOperationException {
        System.out.println("Enter id of " + var + ". For to cancel operation enter command - \"cancel\"");
        String id = scanner.nextLine();
        if(id.length() < 1){
            System.out.println("You enter empty string");
        }
        else{
            if(id.equals("cancel")){
                throw new CloseOperationException("Operation interrupted");
            }
            else{
                try{
                    return new Long(id);
                } catch (InputMismatchException e){
                    System.out.println("You enter incorrect id. Please enter only a number");
                }
            }
        }
        while (id.length() < 1);
        return null;
    }
    public void start(){
        mainMenu();
    }

}
