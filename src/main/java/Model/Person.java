package Model;

import java.util.Date;
import java.util.List;

public class Person {

    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private Date birthday;
    private Date creationDate;
    private String location;
    private String browserUsed;
    private int place;
    private List<Post> postList;

    public Person(String id, String firstName, String lastName, String gender, Date birthday, Date creationDate, String location, String browserUsed, int place) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.creationDate = creationDate;
        this.location = location;
        this.browserUsed = browserUsed;
        this.place = place;
    }
}
