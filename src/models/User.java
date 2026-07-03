package models;

public class User {
    private String userId;
    private String username;
    private String password;
    private String area;
    private String name;

    public User(String userId, String name, String username, String password, String area) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.area = area;
    }

    public String getName() { return name; }

    public String getArea() {
        return area;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return userId + "," + name + "," + username + "," + password + "," + area;
    }
}