package main.java.com.stanislav.crudapp.repository.io;

import main.java.com.stanislav.crudapp.exceptions.EmptyFileException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaIOHelpRepositoryImpl{
    private String helpFilePath =  "src/main/java/com/stanislav/crudapp/resources/help.txt";
    private File help = new File(helpFilePath);
    public void writeData() {

    }

    public List<String> readData(String helpChapter) {
        List<String> helpList = new ArrayList<>();
        try (BufferedReader bfr = new BufferedReader(new FileReader(help))) {
            int c;
            do{
                c = bfr.read();
                if(c == -1){
                    try {
                        throw new EmptyFileException("DataBase is empty");
                    } catch (EmptyFileException emptyFileExecption) {
                        //System.out.println(emptyFileExecption.getMessage());
                    }
                }
                else if(c =='#'){
                    if(bfr.readLine().equals(helpChapter)){
                        String helpLine;
                        do{
                            helpLine = bfr.readLine();
                            if(helpLine != null){
                                helpList.add(helpLine);
                            }
                            else{
                                break;
                            }
                        }
                        while (!helpLine.substring(0,1).equals("#"));
                    }
                }
            }
            while(c!= -1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return helpList;
    }
}
