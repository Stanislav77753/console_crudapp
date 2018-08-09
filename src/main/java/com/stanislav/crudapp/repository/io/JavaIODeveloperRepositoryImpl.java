package main.java.com.stanislav.crudapp.repository.io;

import main.java.com.stanislav.crudapp.exceptions.EmptyFileException;
import main.java.com.stanislav.crudapp.model.Account;
import main.java.com.stanislav.crudapp.model.Developer;
import main.java.com.stanislav.crudapp.model.Skill;
import main.java.com.stanislav.crudapp.repository.DeveloperRepository;

import java.io.*;
import java.util.*;

public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {
    private String developerFilePath = "src/main/java/com/stanislav/crudapp/resources/developers.txt";
    private File developers = new File(developerFilePath);

    @Override
    public void save(Developer developer) {
        try (BufferedWriter out = new BufferedWriter( new FileWriter(developers, true))){
            recordInFile(out, developer);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Developer getById(Long id) {
        return null;
    }

    @Override
    public List<Developer> getAll() throws EmptyFileException {
        List<Developer> allDevelopers= new ArrayList();
        int countId = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(developers))) {
            String str;
            do{
                str = in.readLine();
                if(str == null && countId == 0){
                    throw new EmptyFileException("DataBase is empty");
                }
                else if (str != null){
                    String[] arrayId = str.trim().split(" ");
                    String[] arrayDeveloper = new String[7];
                    arrayDeveloper[0] = arrayId[2];
                    for(int i = 0; i < 6; i++){
                        str = in.readLine();
                        String[] array = str.trim().split(" ");
                        arrayDeveloper[i+1] = array[2];
                    }
                    String[] arraySkills = arrayDeveloper[5].trim().split(",");
                    Set<Skill> skills = new HashSet<>();
                    for(int i = 0; i < arraySkills.length; i++){
                        skills.add(new Skill(null,arraySkills[i]));
                    }
                    allDevelopers.add(new Developer(new Long(arrayDeveloper[0]), arrayDeveloper[1], arrayDeveloper[2],
                            arrayDeveloper[3],arrayDeveloper[4], skills, new Account(null, arrayDeveloper[6])));
                }
                countId++;
            }
            while(str != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allDevelopers;
    }

    @Override
    public void delete(Developer developer) throws EmptyFileException {
        List<Developer> allDevelopers = getAll();
        if(developer.getId() == null){
            try (BufferedWriter out = new BufferedWriter( new FileWriter(developers))){
                for(Developer dev: allDevelopers){
                    if(!dev.getLogin().equals(developer.getLogin())){
                        recordInFile(out, dev);
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
            try (BufferedWriter out = new BufferedWriter( new FileWriter(developers))){
                for(Developer dev: allDevelopers){
                    if(!dev.getId().equals(developer.getId())){
                        recordInFile(out, dev);
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
    public void update(Developer developer) throws EmptyFileException {
        List<Developer> allDevelopers = getAll();
        try (BufferedWriter out = new BufferedWriter( new FileWriter(developers))){
            for(Developer dev: allDevelopers){
                if(!dev.getId().equals(developer.getId())){
                    recordInFile(out, dev);
                }
                else{
                    recordInFile(out, developer);
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recordInFile(BufferedWriter out, Developer developer) throws IOException {
        out.write("ID - " + developer.getId() + "\r\n");
        out.write("LOGIN - " + developer.getLogin() + "\r\n");
        out.write("PASSWORD - " + developer.getPassword() + "\r\n");
        out.write("FIRSTNAME - " + developer.getFirstName() + "\r\n");
        out.write("LASTNAME - " + developer.getLastName() + "\r\n");
        out.write("SkillsId - ");
        int skillsSetSize = developer.getSkills().size();
        StringBuffer sb = new StringBuffer();
        int countSkill = 1;
        for(Skill skills : developer.getSkills()){
            countSkill++;
            if(countSkill == skillsSetSize){
                sb.append(skills.getId());
            }
            else{
                sb.append(skills.getId() + ",");
            }
        }
        out.write(String.valueOf(sb) + "\r\n" );
        out.write("Account - " + developer.getAccount().getDeveloperData() + "\r\n");
    }

    public List<String> getDeveloperListString() throws EmptyFileException {
        List<String> allDevelopers = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        int countId = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(developers))) {
            String str;
            do{
                str = in.readLine();
                if(str == null && countId == 0){
                    throw new EmptyFileException("DataBase is empty");
                }
                else if (str != null){
                    String[] stringWithId = str.trim().replace(" - ", "-").split("-");
                    sb.append(stringWithId[1] + ";");
                    for(int i = 0; i < 6; i++){
                        String[] stringDeveloperData = str.trim().replace(" - ", "-").split("-");
                        if(i == 5){
                            sb.append(stringDeveloperData[1]);
                        }
                        else{
                            sb.append(stringDeveloperData[1] + ";");
                        }
                    }
                }
                String account = String.valueOf(sb);
                allDevelopers.add(account);
                countId++;
            }
            while(str != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allDevelopers;
    }
}
