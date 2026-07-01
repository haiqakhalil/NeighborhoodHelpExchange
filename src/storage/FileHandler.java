package storage;

import models.User;
import models.Post;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private static final String USERS_FILE = "data/users.txt";
    private static final String POSTS_FILE = "data/posts.txt";


    public static void saveUser(User user) {
        try {
            FileWriter fw = new FileWriter(USERS_FILE, true);
            fw.write(user.toString() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            FileReader fr = new FileReader(USERS_FILE);
            Scanner sc = new Scanner(fr);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    User user = new User(parts[0], parts[1], parts[2], parts[3]);
                    users.add(user);
                }
            }
            fr.close();
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    public static void createFilesIfNotExist() {
        try {
            File dataFolder = new File("data");
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            File usersFile = new File(USERS_FILE);
            if (!usersFile.exists()) {
                usersFile.createNewFile();
            }

            File postsFile = new File(POSTS_FILE);
            if (!postsFile.exists()) {
                postsFile.createNewFile();
            }

        } catch (IOException e) {
            System.out.println("Error creating files: " + e.getMessage());
        }
    }


    public static void savePost(Post post) {
        try {
            FileWriter fw = new FileWriter(POSTS_FILE, true);
            fw.write(post.toString() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving post: " + e.getMessage());
        }
    }

    public static ArrayList<Post> loadPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            FileReader fr = new FileReader(POSTS_FILE);
            Scanner sc = new Scanner(fr);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    Post post = new Post(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    posts.add(post);
                }
            }
            fr.close();
        } catch (IOException e) {
            System.out.println("Error loading posts: " + e.getMessage());
        }
        return posts;
    }
}