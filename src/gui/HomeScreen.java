package gui;

import console.MatchEngine;
import models.Post;
import models.User;
import storage.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HomeScreen extends JFrame {

    private User loggedInUser;
    private JPanel postsPanel;

    public HomeScreen(User user) {
        this.loggedInUser = user;

        setTitle("Neighborhood Help Exchange - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(600, 500));
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245));

        // ── TOP PANEL ──
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 245, 245));
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 20));

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getName() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        welcomeLabel.setForeground(new Color(34, 139, 34));

        JLabel title = new JLabel("Community Posts");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(50, 50, 50));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(245, 245, 245));
        titlePanel.add(welcomeLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(title);

        topPanel.add(titlePanel, BorderLayout.WEST);

        // ── CENTER PANEL (posts) ──
        postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        postsPanel.setBackground(new Color(245, 245, 245));
        postsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JScrollPane scrollPane = new JScrollPane(postsPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        loadPosts();

        // ── BOTTOM PANEL (buttons) ──
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(230, 230, 230));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel leftButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftButtons.setBackground(new Color(230, 230, 230));

        JButton postHelpButton = new JButton("+ Post Help");
        postHelpButton.setBackground(new Color(34, 139, 34));
        postHelpButton.setForeground(Color.WHITE);
        postHelpButton.setFont(new Font("Arial", Font.BOLD, 13));
        postHelpButton.setFocusPainted(false);
        postHelpButton.setBorderPainted(false);
        postHelpButton.setPreferredSize(new Dimension(140, 38));

        JButton dashboardButton = new JButton("Dashboard");
        dashboardButton.setBackground(new Color(70, 130, 180));
        dashboardButton.setForeground(Color.WHITE);
        dashboardButton.setFont(new Font("Arial", Font.BOLD, 13));
        dashboardButton.setFocusPainted(false);
        dashboardButton.setBorderPainted(false);
        dashboardButton.setPreferredSize(new Dimension(140, 38));

        leftButtons.add(postHelpButton);
        leftButtons.add(dashboardButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(178, 34, 34));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 13));
        logoutButton.setFocusPainted(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setPreferredSize(new Dimension(120, 38));

        bottomPanel.add(leftButtons, BorderLayout.WEST);
        bottomPanel.add(logoutButton, BorderLayout.EAST);

        // ── ACTION LISTENERS ──
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
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        HomeScreen.this,
                        "Are you sure you want to logout?",
                        "Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                    new LoginScreen();
                }
            }
        });

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadPosts() {
        postsPanel.removeAll();

        List<Post> allPosts = FileHandler.loadPosts();

        if (allPosts.isEmpty()) {
            JLabel noPostsLabel = new JLabel("No posts yet. Be the first to post!");
            noPostsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            noPostsLabel.setForeground(Color.GRAY);
            postsPanel.add(noPostsLabel);
        }

        for (Post post : allPosts) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
            card.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Left side of card
            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            leftPanel.setBackground(Color.WHITE);

            Color typeColor = post.getType().equals("REQUEST") ?
                    new Color(178, 34, 34) : new Color(34, 139, 34);

            JLabel typeLabel = new JLabel(post.getType());
            typeLabel.setFont(new Font("Arial", Font.BOLD, 12));
            typeLabel.setForeground(Color.WHITE);
            typeLabel.setBackground(typeColor);
            typeLabel.setOpaque(true);
            typeLabel.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8));
            typeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel descLabel = new JLabel(post.getDescription());
            descLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel statusLabel = new JLabel("● " + post.getStatus());
            statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
            statusLabel.setForeground(post.getStatus().equals("OPEN") ?
                    new Color(34, 139, 34) : Color.GRAY);
            statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            leftPanel.add(typeLabel);
            leftPanel.add(Box.createVerticalStrut(6));
            leftPanel.add(descLabel);
            leftPanel.add(Box.createVerticalStrut(4));
            leftPanel.add(statusLabel);

            // Right side of card — accept button
            JButton acceptButton = new JButton("Accept");
            acceptButton.setBackground(new Color(34, 139, 34));
            acceptButton.setForeground(Color.WHITE);
            acceptButton.setFont(new Font("Arial", Font.BOLD, 12));
            acceptButton.setFocusPainted(false);
            acceptButton.setBorderPainted(false);
            acceptButton.setPreferredSize(new Dimension(110, 35));

            if (post.getStatus().equals("MATCHED") ||
                    post.getUserId().equals(loggedInUser.getUserId())) {
                acceptButton.setEnabled(false);
                acceptButton.setBackground(Color.LIGHT_GRAY);
            }

            acceptButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean success = MatchEngine.acceptPost(
                            post.getPostId(), loggedInUser.getUserId());
                    if (success) {
                        JOptionPane.showMessageDialog(HomeScreen.this,
                                "You have accepted this post!",
                                "Matched!", JOptionPane.INFORMATION_MESSAGE);
                        loadPosts();
                    } else {
                        JOptionPane.showMessageDialog(HomeScreen.this,
                                "This post is already matched!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            JPanel rightPanel = new JPanel(new GridBagLayout());
            rightPanel.setBackground(Color.WHITE);
            rightPanel.add(acceptButton);

            card.add(leftPanel, BorderLayout.CENTER);
            card.add(rightPanel, BorderLayout.EAST);

            postsPanel.add(card);
            postsPanel.add(Box.createVerticalStrut(10));
        }

        postsPanel.revalidate();
        postsPanel.repaint();
    }
}