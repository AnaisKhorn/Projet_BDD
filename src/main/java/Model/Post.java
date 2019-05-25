package Model;

import java.util.Date;
import java.util.List;

public class Post {
    private String id;
    private Date creationDate;
    private String location;
    private String browserUsed;
    private String content;
    private double length;
    private Person creator;

    public Post(String id, Date creationDate, String location, String browserUsed, String content, double length) {
        this.id = id;
        this.creationDate = creationDate;
        this.location = location;
        this.browserUsed = browserUsed;
        this.content = content;
        this.length = length;
    }
}
