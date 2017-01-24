/**
 * Created by VINNI on 22.01.17.
 */
public class User {

    private long id;
    private String login;
    //private String password;

    public User(long id, String login) {
        this.id = id;
        this.login = login;
    }

    public User (){}

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }




    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
