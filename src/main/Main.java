package main;

import gui.LoginScreen;
import storage.FileHandler;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        FileHandler.createFilesIfNotExist();
        SwingUtilities.invokeLater(LoginScreen::new);
    }
}