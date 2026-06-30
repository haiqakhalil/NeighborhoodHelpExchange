package main;

import model.User;
import model.Post;

public class Main {
    public static void main(String[] args) {
        User u = new User("U1", "haiqa", "1234", "Lahore");
        System.out.println(u);

        Post p = new Post("P1", "U1", "REQUEST", "Need a math tutor", "OPEN");
        System.out.println(p);
    }
}