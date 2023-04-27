package cogent.university.com.DoConnectBackend.entity;

public class JwtResponse {
    private String token;

    private int id;
    private String username;
    private String usertype;

    public JwtResponse(String token, int id, String username, String usertype) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.usertype = usertype;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
