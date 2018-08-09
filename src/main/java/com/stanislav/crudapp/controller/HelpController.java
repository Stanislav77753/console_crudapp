package main.java.com.stanislav.crudapp.controller;

import main.java.com.stanislav.crudapp.service.HelpService;

public class HelpController {
    HelpService helpService = new HelpService();

    public void getHelp(String chapter){
        helpService.getHelp(chapter);
    }
}
