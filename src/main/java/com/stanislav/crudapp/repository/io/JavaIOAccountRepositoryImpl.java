package main.java.com.stanislav.crudapp.repository.io;

import main.java.com.stanislav.crudapp.exceptions.EmptyFileException;
import main.java.com.stanislav.crudapp.model.Account;
import main.java.com.stanislav.crudapp.repository.GenericRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaIOAccountRepositoryImpl implements GenericRepository<Account, Long> {

    private String accountFilePath = "src/main/java/com/stanislav/crudapp/resources/accounts.txt";
    private File accounts = new File(accountFilePath);

    @Override
    public void save(Account account) {
        try (BufferedWriter out = new BufferedWriter( new FileWriter(accounts, true))){
            out.write(account.getId() + " - " + account.getDeveloperData() + "\r\n");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account getById(Long id) throws EmptyFileException {
        int countId = 0;
        try (BufferedReader in = new BufferedReader( new FileReader(accounts))){
            String str;
            do{
                str= in.readLine();
                if(str == null && countId == 0){
                    throw new EmptyFileException("DataBase is empty");
                }
                String[] array = str.trim().replace(" - ", "-").split("-");
                if(new Long(array[0]).equals(id)){
                    return new Account(id, array[1]);
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
    public List<Account> getAll() throws EmptyFileException {
        List<Account> allAccounts= new ArrayList();
        int countId = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(accounts))) {
            String str;
            do{
                str= in.readLine();
                if(str == null && countId == 0){
                    throw new EmptyFileException("DataBase is empty");
                }
                else if (str != null){
                    String[] array = str.trim().replace(" - ", "-").split("-");
                    allAccounts.add(new Account(new Long(array[0]), array[1]));
                }
                countId++;
            }
            while(str != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allAccounts;
    }

    @Override
    public void delete(Account account) throws EmptyFileException {
        List<String> allAccount = getAccountsInListString();
        if(account.getId() == null){
            try (BufferedWriter out = new BufferedWriter( new FileWriter(accounts))){
                for(String stringAccount: allAccount){
                    String[] array = stringAccount.trim().replace(" - ", "-").split("-");
                    if(!array[1].equals(account.getDeveloperData())){
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
            try (BufferedWriter out = new BufferedWriter( new FileWriter(accounts))){
                for(String stringAccount: allAccount){
                    String[] array = stringAccount.trim().replace(" - ", "-").split("-");
                    if(!new Long(array[0]).equals(account.getId())){
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
    }

    @Override
    public void update(Account account) throws EmptyFileException {
        List<Account> allAccounts = getAll();
        try (BufferedWriter out = new BufferedWriter( new FileWriter(accounts))){
            for(Account ac: allAccounts){
                if(!ac.getId().equals(account.getId())){
                    out.write(ac.getId() + " - " + ac.getDeveloperData() + "\r\n");
                }
                else{
                    out.write(account.getId() + " - " + account.getDeveloperData() + "\r\n");
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAccountsInListString() throws EmptyFileException {
        List<String> allAccounts= new ArrayList();
        int countId = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(accounts))) {
            String str;
            do{
                str= in.readLine();
                if(str == null && countId == 0){
                    throw new EmptyFileException("DataBase is empty");
                }
                else if (str != null){
                    allAccounts.add(str);
                }
                countId++;
            }
            while(str != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allAccounts;
    }
}
