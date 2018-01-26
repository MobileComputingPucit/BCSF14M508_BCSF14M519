package pk.edu.pucit.bcsf14m508_19.notes.Models;

/**
 * Created by abc on 1/20/18.
 *
 * @package pk.edu.pucit.mobilecomputing.database.Models
 * @project Database
 */

public class UserInfo {
    private String title;
    private String body;
    private String date;
    private int id;

    public UserInfo(int id, String title, String body, String date) {
        setId(id);
        setTitle(title);
        setBody(body);
        setDate(date);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
