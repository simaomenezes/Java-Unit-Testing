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
}
