package main.java.com.stanislav.crudapp.service;

import main.java.com.stanislav.crudapp.exceptions.EmptyFileException;
import main.java.com.stanislav.crudapp.exceptions.SkillAlreadyExistException;
import main.java.com.stanislav.crudapp.model.Skill;
import main.java.com.stanislav.crudapp.repository.io.JavaIOSkillRepositoryImpl;

import java.util.List;

public class SkillService {
    JavaIOSkillRepositoryImpl javaIOSkillRepository = new JavaIOSkillRepositoryImpl();

    public void addSkill(Skill skill) throws SkillAlreadyExistException {
        Long id = 0L;
        try {
            List<String> allSkill = javaIOSkillRepository.getSkillsInListString();
            for(String stringSkill: allSkill){
                String[] strArray = stringSkill.trim().replace(" - ", "-").split("-");
                if(strArray[1].equals(skill.getName())){
                    throw new SkillAlreadyExistException("This skill already exist");
                }
                id = new Long(strArray[0]) + 1L;
            }
        } catch (EmptyFileException e) {
            id = 1L;
        }
        skill.setId(id);
        javaIOSkillRepository.save(skill);
    }

    public void getAllSkills() throws EmptyFileException {
        List<String> allSkillFromFile = javaIOSkillRepository.getSkillsInListString();
        for(String skill: allSkillFromFile){
            System.out.println(skill);
        }
    }
    public void deleteSkill(Skill skill) throws EmptyFileException {
        javaIOSkillRepository.delete(skill);
    }
    public void updateSkillById(Skill skill) throws EmptyFileException {
        javaIOSkillRepository.update(skill);
    }

    public Skill getSkillById(Long id) throws EmptyFileException {
        return javaIOSkillRepository.getById(id);
    }
}
