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
        setSize(450, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));

        title = new JLabel("🏘 Neighborhood Help Exchange");
        title.setBounds(40, 30, 370, 35);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(new Color(34, 139, 34));

        JLabel subtitle = new JLabel("Login to your account");
        subtitle.setBounds(40, 65, 300, 20);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitle.setForeground(Color.GRAY);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 120, 100, 25);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 13));

        usernameField = new JTextField();
        usernameField.setBounds(160, 120, 200, 30);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 13));
        usernameField.setBorder(BorderFactory.createLineBorder(
                new Color(34, 139, 34), 1));

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 170, 100, 25);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 13));

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 170, 200, 30);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 13));
        passwordField.setBorder(BorderFactory.createLineBorder(
                new Color(34, 139, 34), 1));

        loginButton = new JButton("Login");
        loginButton.setBounds(80, 240, 120, 35);
        loginButton.setBackground(new Color(34, 139, 34));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);

        registerButton = new JButton("Register");
        registerButton.setBounds(230, 240, 120, 35);
        registerButton.setBackground(new Color(70, 130, 180));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);

        add(title);
        add(subtitle);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText().trim();
                String enteredPassword = new String(
                        passwordField.getPassword()).trim();

                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginScreen.this,
                            "Please enter username and password",
                            "Login Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<User> users = FileHandler.loadUsers();
                for (User user : users) {
                    if (user.getUsername().equals(enteredUsername)
                            && user.getPassword().equals(enteredPassword)) {
                        new HomeScreen(user);
                        dispose();
                        return;
                    }
                }

                JOptionPane.showMessageDialog(LoginScreen.this,
                        "Invalid username or password",
                        "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterScreen();
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}