package main;

import gui.LoginScreen;
import storage.FileHandler;
import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileHandler.createFilesIfNotExist();
        SwingUtilities.invokeLater(LoginScreen::new);
    }
}