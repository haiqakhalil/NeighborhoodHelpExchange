package models;

public class Match {
    private String matchId;
    private String postId;
    private String requesterId;
    private String helperId;

    public Match(String matchId, String postId, String requesterId, String helperId) {
        this.matchId = matchId;
        this.postId = postId;
        this.requesterId = requesterId;
        this.helperId = helperId;
    }

    public String getMatchId() { return matchId; }
    public String getPostId() { return postId; }
    public String getRequesterId() { return requesterId; }
    public String getHelperId() { return helperId; }

    @Override
    public String toString() {
        return matchId + "," + postId + "," + requesterId + "," + helperId;
    }
}