package gui;

import models.User;
import storage.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginScreen extends JFrame {

    private JLabel title;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginScreen() {
        setTitle("Neighborhood Help Exchange - Login");
        setSize(400, 350);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title = new JLabel("Neighborhood Help Exchange");
        title.setBounds(50, 20, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 16));

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 90, 100, 25);

        usernameField = new JTextField();
        usernameField.setBounds(150, 90, 180, 25);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 130, 100, 25);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 130, 180, 25);

        loginButton = new JButton("Login");
        loginButton.setBounds(80, 190, 100, 30);

        registerButton = new JButton("Register");
        registerButton.setBounds(200, 190, 100, 30);

        add(title);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                String enteredPassword = new String(passwordField.getPassword());

                List<User> users = FileHandler.loadUsers();

                for (User user : users) {
                    if (user.getUsername().equals(enteredUsername)
                            && user.getPassword().equals(enteredPassword)) {
                        JOptionPane.showMessageDialog(null, "Login Successful! Welcome " + user.getUsername());
                        dispose();
                        return;
                    }
                }

                JOptionPane.showMessageDialog(
                        LoginScreen.this,
                        "Invalid username or password",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterScreen();
                dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginScreen::new);
    }
}
