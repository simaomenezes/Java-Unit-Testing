package github.com.simaomenezes.model;

public class Person {
    private Long id;
    private String name;
    private String email;
    private String username;

    public Person(String name, String email, String username) {
        this.name = name;
        this.email = email;
        this.username = username;
    }

    public Person() {

    }

    public Person(Long id , String name, String email, String username) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
