package main.java.com.stanislav.crudapp.model;

public class NamedEntity extends BaseEntity {
    private String name;
    public NamedEntity(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
