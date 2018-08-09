package main.java.com.stanislav.crudapp.model;

public class Account extends BaseEntity {
    private String developerData;
    public Account(Long id, String developerData){
       super(id);
       this.developerData = developerData;
    }

    public String getDeveloperData() {
        return developerData;
    }

    public void setDeveloperData(String developerData) {
        this.developerData = developerData;
    }
}
