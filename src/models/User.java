package models;

public class User {
    private String userId;
    private String username;
    private String password;
    private String area;

    public User(String area, String password, String userId, String username) {
        this.area = area;
        this.password = password;
        this.userId = userId;
        this.username = username;
    }

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
        return "User{" +
                "area='" + area + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}