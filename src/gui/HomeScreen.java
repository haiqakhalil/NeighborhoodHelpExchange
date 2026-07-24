package gui;

import console.MatchEngine;
import models.User;
import models.Post;
import storage.FileHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HomeScreen extends JFrame {

    private User loggedInUser;

    private JLabel title;
    private JLabel welcomeLabel;
    private JPanel postsPanel;
    private JScrollPane scrollPane;
    private JButton postHelpButton;
    private JButton dashboardButton;
    private JButton logoutButton;

    public HomeScreen(User user) {
        this.loggedInUser = user;

        setTitle("Neighborhood Help Exchange - Home");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcomeLabel = new JLabel("Welcome, " + user.getName());
        welcomeLabel.setBounds(20, 15, 400, 25);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        title = new JLabel("Community Posts");
        title.setBounds(20, 45, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        postHelpButton = new JButton("Post Help Request");
        postHelpButton.setBounds(20, 420, 160, 30);

        dashboardButton = new JButton("Dashboard");
        dashboardButton.setBounds(200, 420, 140, 30);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(460, 420, 100, 30);

        postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(postsPanel);
        scrollPane.setBounds(20, 85, 540, 320);

        loadPosts();

         postHelpButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        new PostFormScreen(loggedInUser);
        dispose();
    }
});

        dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 new DashboardScreen(loggedInUser);
                 dispose();
               //JOptionPane.showMessageDialog(null, "Coming Soon!");
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScreen();
                dispose();
            }
        });

        add(welcomeLabel);
        add(title);
        add(scrollPane);
        add(postHelpButton);
        add(dashboardButton);
        add(logoutButton);

        setVisible(true);
    }

    private void loadPosts() {
        postsPanel.removeAll();

        List<Post> allPosts = FileHandler.loadPosts();

        for (Post post : allPosts) {
            JPanel card = new JPanel();
            card.setLayout(null);
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.setPreferredSize(new Dimension(520, 100));
            card.setMaximumSize(new Dimension(520, 100));
            card.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel typeLabel = new JLabel("Type: " + post.getType());
            typeLabel.setBounds(10, 10, 400, 20);

            JLabel descLabel = new JLabel("Description: " + post.getDescription());
            descLabel.setBounds(10, 35, 400, 20);

            JLabel statusLabel = new JLabel("Status: " + post.getStatus());
            statusLabel.setBounds(10, 60, 200, 20);

            JButton acceptButton = new JButton("Accept");
            acceptButton.setBounds(400, 35, 100, 25);

            if (post.getStatus().equals("MATCHED") ||
                    post.getUserId().equals(loggedInUser.getUserId())) {
                acceptButton.setEnabled(false);
            }

            acceptButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean success = MatchEngine.acceptPost(post.getPostId(), loggedInUser.getUserId());
                    if (success) {
                        JOptionPane.showMessageDialog(
                                HomeScreen.this,
                                "You have accepted this post!",
                                "Matched!",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        loadPosts();
                    } else {
                        JOptionPane.showMessageDialog(
                                HomeScreen.this,
                                "This post is already matched!",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            });

            card.add(typeLabel);
            card.add(descLabel);
            card.add(statusLabel);
            card.add(acceptButton);

            postsPanel.add(card);
            postsPanel.add(Box.createVerticalStrut(8));
        }

        postsPanel.revalidate();
        postsPanel.repaint();
    }
        }


