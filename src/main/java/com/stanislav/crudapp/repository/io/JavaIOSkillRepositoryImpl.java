package main.java.com.stanislav.crudapp.repository.io;

import main.java.com.stanislav.crudapp.exceptions.EmptyFileException;
import main.java.com.stanislav.crudapp.model.Skill;
import main.java.com.stanislav.crudapp.repository.SkillRepository;

import java.io.*;
import java.util.*;

public class JavaIOSkillRepositoryImpl implements SkillRepository {
    private String skillsFilePath = "src/main/resources/skills.txt";
    private File skills = new File(skillsFilePath);

    @Override
    public void save(Skill skill) {
        try (BufferedWriter out = new BufferedWriter( new FileWriter(skills, true))){
            out.write(skill.getId() + " - " + skill.getName() + "\r\n");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Skill getById(Long id)throws EmptyFileException {
        int countId = 0;
        try (BufferedReader in = new BufferedReader( new FileReader(skills))){
                String str;
                do{
                    str= in.readLine();
                    if(str == null && countId == 0){
                        throw new EmptyFileException("DataBase is empty");
                    }
                    String[] array = str.trim().replace(" - ", "-").split("-");
                    if(new Long(array[0]).equals(id)){
                        return new Skill(id, array[1]);
                    }
                    countId++;
                }
                while (str != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Skill> getAll() throws EmptyFileException {
        List<Skill> allSkills= new ArrayList();
        int countId = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(skills))) {
            String str;
            do{
                str= in.readLine();
                if(str == null && countId == 0){
                    throw new EmptyFileException("DataBase is empty");
                }
                else if (str != null){
                    String[] array = str.trim().replace(" - ", "-").split("-");
                    allSkills.add(new Skill(new Long(array[0]), array[1]));
                }
                countId++;
            }
            while(str != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allSkills;
    }

    @Override
    public void delete(Skill skill)throws EmptyFileException {
        List<String> allSkills = getSkillsInListString();
        if(skill.getId() == null){
            try (BufferedWriter out = new BufferedWriter( new FileWriter(skills))){
                for(String stringSkills: allSkills){
                    String[] array = stringSkills.trim().replace(" - ", "-").split("-");
                    if(!array[1].equals(skill.getName())){
                        out.write(array[0] + " - " + array[1] + "\r\n");
                    }
                }
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try (BufferedWriter out = new BufferedWriter( new FileWriter(skills))){
                for(String stringSkills: allSkills){
                    String[] array = stringSkills.trim().replace(" - ", "-").split("-");
                    if(!new Long(array[0]).equals(skill.getId())){
                        out.write(array[0]+ " - " + array[1] + "\r\n");
                    }
                }
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Skill skill)throws EmptyFileException {
        List<Skill> allSkills = getAll();
        try (BufferedWriter out = new BufferedWriter( new FileWriter(skills))){
            for(Skill skills: allSkills){
                if(!skills.getId().equals(skill.getId())){
                    out.write(skills.getId() + " - " + skills.getName() + "\r\n");
                }
                else{
                    out.write(skill.getId() + " - " + skill.getName() + "\r\n");
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getSkillsInListString() throws EmptyFileException {
        List<String> allSkills = new ArrayList<>();
        int countId = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(skills))) {
            String str;
            do{
                str= in.readLine();
                if(str == null && countId == 0){
                    throw new EmptyFileException("DataBase is empty");
                }
                else if (str != null){
                    allSkills.add(str);
                }
                countId++;
            }
            while(str != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allSkills;
    }
}
