package main.java.com.stanislav.crudapp.view;

import main.java.com.stanislav.crudapp.controller.HelpController;
import main.java.com.stanislav.crudapp.controller.SkillController;
import main.java.com.stanislav.crudapp.exceptions.CloseOperationException;
import main.java.com.stanislav.crudapp.exceptions.EmptyFileException;
import main.java.com.stanislav.crudapp.exceptions.SkillAlreadyExistException;
import main.java.com.stanislav.crudapp.model.Skill;

import java.util.Scanner;

public class SkillView {
    public void skillMenu(){
        Scanner scanner = new Scanner(System.in);
        String command = "";
        SkillController skillController = new SkillController();
        while (!command.equals("close")){
            System.out.println("You are in skill menu, to get help enter command \"help\":");
            System.out.println("Enter command:");
            command = scanner.nextLine().toLowerCase().trim();
            switch(command){
                case "add new skill":
                    try {
                        skillController.addSkill(new Skill(null,ConsoleHelper.getStringFromConsole("new skill")));
                    }catch (SkillAlreadyExistException skillAlreadyExistException) {
                        System.out.println(skillAlreadyExistException.getMessage());
                    }catch (CloseOperationException closeOperationException) {
                        System.out.println(closeOperationException.getMessage());
                    }
                    break;
                case "look all skills" :
                    try {
                        skillController.lookAllSkills();
                    } catch (EmptyFileException emptyFileException) {
                        System.out.println(emptyFileException.getMessage());
                    }
                    break;
                case "delete skill by id" :
                    try {
                        skillController.deleteSkill(new Skill(ConsoleHelper.getIdFromConsole("skill"), null));
                    } catch (EmptyFileException emptyFileException) {
                        System.out.println(emptyFileException.getMessage());
                    }catch (CloseOperationException closeOperationException) {
                        System.out.println(closeOperationException.getMessage());
                    }
                    break;
                case "delete skill by skillname" :
                    try {
                        skillController.deleteSkill(new Skill(null,ConsoleHelper.getStringFromConsole("skill")));
                    } catch (EmptyFileException emptyFileException) {
                        System.out.println(emptyFileException.getMessage());
                    }catch (CloseOperationException closeOperationException) {
                        System.out.println(closeOperationException.getMessage());
                    }
                    break;
                case "update skill by id" :
                    try {
                        skillController.updateSkill(new Skill(ConsoleHelper.getIdFromConsole("skill"),
                                ConsoleHelper.getStringFromConsole("new skill")));
                    } catch (EmptyFileException emptyFileException) {
                        System.out.println(emptyFileException.getMessage());
                    } catch (CloseOperationException closeOperationExceptiom) {
                        System.out.println(closeOperationExceptiom.getMessage());
                    }
                    break;
                case "help":
                    new HelpController().getHelp("SKILL");
                    break;
            }
        }
    }
}
