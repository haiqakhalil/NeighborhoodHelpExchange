package gui;

import models.Post;
import models.User;
import storage.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardScreen extends JFrame {

    private User loggedInUser;
    private JPanel postsPanel;

    public DashboardScreen(User user) {
        this.loggedInUser = user;

        setTitle("Neighborhood Help Exchange - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(600, 500));
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245));

        // ── TOP PANEL ──
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 245, 245));
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 20));

        JLabel welcomeLabel = new JLabel("My Dashboard — " + user.getName());
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        welcomeLabel.setForeground(new Color(70, 130, 180));

        JLabel title = new JLabel("My Posts");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(50, 50, 50));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(245, 245, 245));
        titlePanel.add(welcomeLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(title);

        topPanel.add(titlePanel, BorderLayout.WEST);

        // ── CENTER PANEL ──
        postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        postsPanel.setBackground(new Color(245, 245, 245));
        postsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JScrollPane scrollPane = new JScrollPane(postsPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        loadMyPosts();

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(NavBar.create(loggedInUser, "dashboard", this), BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadMyPosts() {
        postsPanel.removeAll();

        List<Post> allPosts = FileHandler.loadPosts();
        boolean hasPosts = false;

        for (Post post : allPosts) {
            if (post.getUserId().equals(loggedInUser.getUserId())) {
                hasPosts = true;

                JPanel card = new JPanel(new BorderLayout());
                card.setBackground(Color.WHITE);
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                        BorderFactory.createEmptyBorder(10, 15, 10, 15)
                ));
                card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
                card.setAlignmentX(Component.LEFT_ALIGNMENT);

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

                card.add(leftPanel, BorderLayout.CENTER);

                postsPanel.add(card);
                postsPanel.add(Box.createVerticalStrut(10));
            }
        }

        if (!hasPosts) {
            JLabel noPostsLabel = new JLabel("You have no posts yet. Click Post Help to create one!");
            noPostsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            noPostsLabel.setForeground(Color.GRAY);
            postsPanel.add(noPostsLabel);
        }

        postsPanel.revalidate();
        postsPanel.repaint();
    }
}