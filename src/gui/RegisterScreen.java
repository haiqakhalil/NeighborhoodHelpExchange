package gui;

import models.User;
import storage.FileHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RegisterScreen extends JFrame {
    private JLabel title;
    private JLabel nameLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel areaLabel;
    private JTextField usernameField;
    private JTextField nameField;
    private JTextField areaField;
    private JPasswordField passwordField;
    private JButton backButton;
    private JButton registerButton;

    public RegisterScreen() {
        setTitle("Neighborhood Help Exchange - Register");
        setSize(400, 450);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title = new JLabel("Create an Account");
        title.setBounds(50, 20, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 16));

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 80, 100, 25);

        nameField = new JTextField();
        nameField.setBounds(150, 80, 180, 25);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 120, 100, 25);

        usernameField = new JTextField();
        usernameField.setBounds(150, 120, 180, 25);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 160, 100, 25);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 160, 180, 25);

        areaLabel = new JLabel("Area:");
        areaLabel.setBounds(50, 200, 100, 25);

        areaField = new JTextField();
        areaField.setBounds(150, 200, 180, 25);

        registerButton = new JButton("Register");
        registerButton.setBounds(80, 260, 100, 30);

        backButton = new JButton("Back");
        backButton.setBounds(200, 260, 100, 30);

        add(title);
        add(nameLabel);
        add(nameField);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(areaLabel);
        add(areaField);
        add(registerButton);
        add(backButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String area = areaField.getText().trim();

                if (name.isEmpty() || username.isEmpty() || password.isEmpty() || area.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            RegisterScreen.this,
                            "Please fill in all fields",
                            "Registration Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                List<User> users = FileHandler.loadUsers();

                for (User user : users) {
                    if (user.getUsername().equals(username)) {
                        JOptionPane.showMessageDialog(
                                RegisterScreen.this,
                                "Username already taken",
                                "Registration Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                }

                String userId = "U" + (users.size() + 1);
                User newUser = new User(name,userId, username, password, area);
                FileHandler.saveUser(newUser);

                JOptionPane.showMessageDialog(
                        RegisterScreen.this,
                        "Registered successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                new LoginScreen();
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScreen();
                dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegisterScreen::new);
    }
}
