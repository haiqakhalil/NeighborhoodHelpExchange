package console;

import models.Match;
import models.Post;
import storage.FileHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatchEngine {

    public static boolean acceptPost(String postId, String helperId) {


        List<Post> allPosts = FileHandler.loadPosts();

        boolean found = false;
        Post matchedPost = null;

        for (Post post : allPosts) {
            if (post.getPostId().equals(postId)) {
                if (post.getStatus().equals("MATCHED")) {
                    return false;
                }
                post.setStatus("MATCHED");
                matchedPost = post;
                found = true;
                break;
            }
        }

        if (!found) return false;


        try {
            FileWriter fw = new FileWriter("data/posts.txt", false);
            for (Post post : allPosts) {
                fw.write(post.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error updating posts: " + e.getMessage());
        }


        List<Match> matches = FileHandler.loadMatches();
        String matchId = "M" + (matches.size() + 1);
        Match newMatch = new Match(matchId, postId, matchedPost.getUserId(), helperId);
        FileHandler.saveMatch(newMatch);

        return true;
    }
}