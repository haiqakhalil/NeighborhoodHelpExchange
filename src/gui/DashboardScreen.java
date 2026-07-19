package gui;

import models.Post;
import models.User;
import storage.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DashboardScreen extends JFrame {

    private User loggedInUser;

    private JLabel title;
    private JLabel welcomeLabel;
    private JPanel postsPanel;
    private JScrollPane scrollPane;
    private JButton homeButton;
    private JButton logoutButton;

    public DashboardScreen(User user) {
        this.loggedInUser = user;

        setTitle("Neighborhood Help Exchange - Dashboard");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Welcome label
        welcomeLabel = new JLabel("My Dashboard — " + user.getName());
        welcomeLabel.setBounds(20, 15, 400, 25);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Title
        title = new JLabel("My Posts");
        title.setBounds(20, 45, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        // Buttons
        homeButton = new JButton("Back to Home");
        homeButton.setBounds(20, 420, 150, 30);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(460, 420, 100, 30);

        // Posts panel
        postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));

        // Scroll pane
        scrollPane = new JScrollPane(postsPanel);
        scrollPane.setBounds(20, 85, 540, 320);

        // Load only this user's posts
        loadMyPosts();

        // Home button
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeScreen(loggedInUser);
                dispose();
            }
        });

        // Logout button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScreen();
                dispose();
            }
        });

        // Add all components
        add(welcomeLabel);
        add(title);
        add(scrollPane);
        add(homeButton);
        add(logoutButton);

        setVisible(true);
    }

    private void loadMyPosts() {
        postsPanel.removeAll();

        List<Post> allPosts = FileHandler.loadPosts();

        boolean hasPosts = false;

        for (Post post : allPosts) {
            // Only show posts that belong to logged in user
            if (post.getUserId().equals(loggedInUser.getUserId())) {
                hasPosts = true;

                JPanel card = new JPanel();
                card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
                card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                card.setMaximumSize(new Dimension(520, 90));
                card.setAlignmentX(Component.LEFT_ALIGNMENT);

                JLabel typeLabel = new JLabel("Type: " + post.getType());
                JLabel descLabel = new JLabel("Description: " + post.getDescription());
                JLabel statusLabel = new JLabel("Status: " + post.getStatus());

                card.add(typeLabel);
                card.add(descLabel);
                card.add(statusLabel);

                postsPanel.add(card);
                postsPanel.add(Box.createVerticalStrut(8));
            }
        }

        // Show message if no posts found
        if (!hasPosts) {
            JLabel noPostsLabel = new JLabel("You have no posts yet. Click Post Help Request to create one!");
            noPostsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            postsPanel.add(noPostsLabel);
        }

        postsPanel.revalidate();
        postsPanel.repaint();
    }
}