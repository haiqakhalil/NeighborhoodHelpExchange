package models;

public class Post {
    private String postId;
    private String userId;
    private String type;
    private String description;
    private String status;

    public Post(String postId, String userId, String type, String description, String status) {
        this.postId = postId;
        this.userId = userId;
        this.type = type;
        this.description = description;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public String getPostId() {
        return postId;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getUserId() {
        return userId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override

    public String toString() {
        return postId + "," + userId + "," + type + "," + description + "," + status;
    }

}