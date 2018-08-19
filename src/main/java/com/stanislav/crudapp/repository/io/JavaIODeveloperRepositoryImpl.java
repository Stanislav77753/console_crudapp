package main.java.com.stanislav.crudapp.repository.io;

import main.java.com.stanislav.crudapp.exceptions.EmptyFileException;
import main.java.com.stanislav.crudapp.model.Account;
import main.java.com.stanislav.crudapp.model.Developer;
import main.java.com.stanislav.crudapp.model.Skill;
import main.java.com.stanislav.crudapp.repository.DeveloperRepository;

import java.io.*;
import java.util.*;

public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {
    private String developerFilePath = "src/main/resources/developers.txt";
    private File developers = new File(developerFilePath);

    /**
     * This method saves the entity to a file
     * @param developer
     */
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

    /**
     * This method returns the collection of entities from file
     * @return
     * @throws EmptyFileException
     */
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
                    String[] arrayId = str.trim().replace(" - ", "-").split("-");
                    String[] arrayDeveloper = new String[7];
                    arrayDeveloper[0] = arrayId[1];
                    for(int i = 0; i < 6; i++){
                        str = in.readLine();
                        String[] array = str.trim().replace(" - ", "-").split("-");
                        arrayDeveloper[i+1] = array[1];
                    }
                    String[] arraySkills = arrayDeveloper[5].trim().split(",");
                    Set<Skill> skills = new HashSet<>();
                    for(int i = 0; i < arraySkills.length; i++){
                        skills.add(new Skill(new Long(arraySkills[i]),null));
                    }
                    allDevelopers.add(new Developer(new Long(arrayDeveloper[0]), arrayDeveloper[1], arrayDeveloper[2],
                            arrayDeveloper[3],arrayDeveloper[4], skills, new Account(new Long(arrayDeveloper[6]), null)));
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

    /**
     * This method removes the entity by id or login from file
     * @param developer
     * @throws EmptyFileException
     */
    @Override
    public void delete(Developer developer) throws EmptyFileException {
        List<String> allDevelopers = getDeveloperListString();
        if(developer.getId() == null){
            try (BufferedWriter out = new BufferedWriter( new FileWriter(developers))){
                for(String stringDeveloper: allDevelopers){
                    String[] array = stringDeveloper.trim().split(";");
                    if(!array[1].equals(developer.getLogin())){
                        String[] arraySkillId = array[5].trim().split(",");
                        Set<Skill> set = new HashSet<>();
                        for(int i = 0; i < arraySkillId.length; i++){
                            set.add(new Skill(new Long(arraySkillId[i]), null));
                        }
                        recordInFile(out, new Developer(new Long(array[0]), array[1], array[2], array[3], array[4], set,
                                new Account(new Long(array[6]), null)));
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
                for(String stringDeveloper: allDevelopers){
                    String[] array = stringDeveloper.trim().split(";");
                    if(!new Long(array[0]).equals(developer.getId())){
                        String[] arraySkillId = array[5].trim().split(",");
                        Set<Skill> set = new HashSet<>();
                        for(int i = 0; i < arraySkillId.length; i++){
                            set.add(new Skill(new Long(arraySkillId[i]), null));
                        }
                        recordInFile(out,new Developer(new Long(array[0]), array[1], array[2], array[3], array[4], set,
                                new Account(new Long(array[6]), null)));
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

    /**
     * This method updates the entity in file by id
     * @param developer
     * @throws EmptyFileException
     */
    @Override
    public void update(Developer developer) throws EmptyFileException {
        List<String> allDevelopers = getDeveloperListString();
        try (BufferedWriter out = new BufferedWriter( new FileWriter(developers))){
            for(String stringDeveloper: allDevelopers){
                String[] array = stringDeveloper.trim().split(";");
                if(!new Long(array[0]).equals(developer.getId())){
                    String[] arraySkillId = array[5].trim().split(",");
                    Set<Skill> set = new HashSet<>();
                    for(int i = 0; i < arraySkillId.length; i++){
                        set.add(new Skill(new Long(arraySkillId[i]), null));
                    }
                    recordInFile(out,new Developer(new Long(array[0]), array[1], array[2], array[3], array[4], set,
                            new Account(new Long(array[6]), null)));
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

    /**
     * This method records the entity in file
     * @param out
     * @param developer
     * @throws IOException
     */
    public void recordInFile(BufferedWriter out, Developer developer) throws IOException {
        out.write("ID - " + developer.getId() + "\r\n");
        out.write("LOGIN - " + developer.getLogin() + "\r\n");
        out.write("PASSWORD - " + developer.getPassword() + "\r\n");
        out.write("FIRSTNAME - " + developer.getFirstName() + "\r\n");
        out.write("LASTNAME - " + developer.getLastName() + "\r\n");
        out.write("SkillsId - ");
        int skillsSetSize = developer.getSkills().size();
        StringBuffer sb = new StringBuffer();
        int countSkill = 0;
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
        out.write("Account ID - " + developer.getAccount().getId() + "\r\n");
    }

    /**
     * This method returns the collection of strings entities from file
     * @return
     * @throws EmptyFileException
     */
    public List<String> getDeveloperListString() throws EmptyFileException {
        List<String> allDevelopers = new ArrayList<>();
        int countId = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(developers))) {
            String str;
            do{
                StringBuffer sb = new StringBuffer();
                str = in.readLine();
                if(str == null && countId == 0){
                    throw new EmptyFileException("DataBase is empty");
                }
                else if (str != null && str !=""){
                    String[] stringWithId = str.trim().replace(" - ", "-").split("-");
                    sb.append(stringWithId[1] + ";");
                    for(int i = 0; i < 6; i++){
                        String[] stringDeveloperData = in.readLine().trim().replace(" - ", "-").
                                split("-");
                        if(i == 5){
                            sb.append(stringDeveloperData[1]);
                        }
                        else{
                            sb.append(stringDeveloperData[1] + ";");
                        }
                    }
                    String developer = String.valueOf(sb);
                    allDevelopers.add(developer);
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
}
