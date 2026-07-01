package main;

import gui.LoginScreen;
import storage.FileHandler;

public class Main {
    public static void main(String[] args) {
        FileHandler.createFilesIfNotExist();
        new LoginScreen();
    }
}