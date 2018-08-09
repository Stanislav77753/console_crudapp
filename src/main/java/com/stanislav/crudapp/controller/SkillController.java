package main.java.com.stanislav.crudapp.controller;

import main.java.com.stanislav.crudapp.exceptions.EmptyFileException;
import main.java.com.stanislav.crudapp.exceptions.SkillAlreadyExistException;
import main.java.com.stanislav.crudapp.model.Skill;
import main.java.com.stanislav.crudapp.service.SkillService;

public class SkillController {
    SkillService skillService = new SkillService();

    public void addSkill(Skill skill) throws SkillAlreadyExistException {
            skillService.addSkill(skill);
    }

    public void lookAllSkills() throws EmptyFileException {
        skillService.getAllSkills();
    }
    public void deleteSkill(Skill skill) throws EmptyFileException {
        skillService.deleteSkill(skill);
    }
    public void updateSkill(Skill skill) throws EmptyFileException {
        skillService.updateSkillById(skill);
    }
    public Skill getSkillById(Long id) throws EmptyFileException {
        return skillService.getSkillById(id);
    }

}
