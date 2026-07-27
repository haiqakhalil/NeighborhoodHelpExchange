package gui;

import models.Post;
import models.User;
import storage.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PostFormScreen extends JFrame {

    private User loggedInUser;
    private JComboBox<String> typeDropdown;
    private JTextArea descriptionArea;

    public PostFormScreen(User user) {
        this.loggedInUser = user;

        setTitle("Neighborhood Help Exchange - Post Help");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(600, 500));
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245));

        // ── TOP PANEL ──
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 245, 245));
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 20));

        JLabel title = new JLabel("Create a Post");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(50, 50, 50));

        topPanel.add(title, BorderLayout.WEST);

        // ── CENTER PANEL ──
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 10, 40));

        JLabel typeLabel = new JLabel("Post Type:");
        typeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        typeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        String[] types = {"REQUEST", "OFFER"};
        typeDropdown = new JComboBox<>(types);
        typeDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        typeDropdown.setMaximumSize(new Dimension(300, 35));
        typeDropdown.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel descLabel = new JLabel("Description:");
        descLabel.setFont(new Font("Arial", Font.BOLD, 14));
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        descriptionArea = new JTextArea(6, 30);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane descScroll = new JScrollPane(descriptionArea);
        descScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        descScroll.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton submitButton = new JButton("Submit Post");
        submitButton.setBackground(new Color(34, 139, 34));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);
        submitButton.setPreferredSize(new Dimension(150, 40));
        submitButton.setMaximumSize(new Dimension(150, 40));
        submitButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        formPanel.add(typeLabel);
        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(typeDropdown);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(descLabel);
        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(descScroll);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(submitButton);

        submitButton.addActionListener(e -> {
            String type = (String) typeDropdown.getSelectedItem();
            String description = descriptionArea.getText().trim();

            if (description.isEmpty()) {
                JOptionPane.showMessageDialog(PostFormScreen.this,
                        "Please enter a description",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<Post> posts = FileHandler.loadPosts();
            String postId = "P" + (posts.size() + 1);

            Post newPost = new Post(postId, loggedInUser.getUserId(),
                    type, description, "OPEN");
            FileHandler.savePost(newPost);

            JOptionPane.showMessageDialog(PostFormScreen.this,
                    "Post submitted successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            new HomeScreen(loggedInUser);
            dispose();
        });

        add(topPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(NavBar.create(loggedInUser, "post", this), BorderLayout.SOUTH);

        setVisible(true);
    }
}