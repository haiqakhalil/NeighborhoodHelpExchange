package gui;

import models.Match;
import models.Post;
import models.User;
import storage.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NotificationScreen extends JFrame {

    private User loggedInUser;
    private JPanel mainPanel;

    public NotificationScreen(User user) {
        this.loggedInUser = user;

        setTitle("Neighborhood Help Exchange - Notifications");
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

        JLabel title = new JLabel("🔔 Notifications");
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
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        loadNotifications();

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(NavBar.create(loggedInUser, "notification", this), BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadNotifications() {
        mainPanel.removeAll();

        List<Match> allMatches = FileHandler.loadMatches();
        List<User> allUsers = FileHandler.loadUsers();
        List<Post> allPosts = FileHandler.loadPosts();

        boolean hasNotifications = false;

        for (Match match : allMatches) {
            if (!match.getRequesterId().equals(loggedInUser.getUserId())) continue;

            hasNotifications = true;

            Post matchedPost = null;
            for (Post post : allPosts) {
                if (post.getPostId().equals(match.getPostId())) {
                    matchedPost = post;
                    break;
                }
            }

            User helper = null;
            for (User user : allUsers) {
                if (user.getUserId().equals(match.getHelperId())) {
                    helper = user;
                    break;
                }
            }

            if (matchedPost == null || helper == null) continue;

            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(new Color(255, 253, 240));
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(255, 200, 0), 2),
                    BorderFactory.createEmptyBorder(12, 15, 12, 15)
            ));
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));
            card.setAlignmentX(Component.LEFT_ALIGNMENT);

            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            leftPanel.setBackground(new Color(255, 253, 240));

            JLabel notifLabel = new JLabel("🔔 Someone wants to help you!");
            notifLabel.setFont(new Font("Arial", Font.BOLD, 13));
            notifLabel.setForeground(new Color(200, 120, 0));
            notifLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel descLabel = new JLabel("📝 Your Post: " + matchedPost.getDescription());
            descLabel.setFont(new Font("Arial", Font.PLAIN, 13));
            descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel helperLabel = new JLabel("👤 Helper: " + helper.getName()
                    + "  |  @" + helper.getUsername()
                    + "  |  📍 " + helper.getArea());
            helperLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            helperLabel.setForeground(new Color(80, 80, 80));
            helperLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            leftPanel.add(notifLabel);
            leftPanel.add(Box.createVerticalStrut(6));
            leftPanel.add(descLabel);
            leftPanel.add(Box.createVerticalStrut(4));
            leftPanel.add(helperLabel);

            card.add(leftPanel, BorderLayout.CENTER);

            mainPanel.add(card);
            mainPanel.add(Box.createVerticalStrut(10));
        }

        if (!hasNotifications) {
            JLabel noNotifLabel = new JLabel("No notifications yet. Post something and wait for someone to accept!");
            noNotifLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            noNotifLabel.setForeground(Color.GRAY);
            mainPanel.add(noNotifLabel);
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }
}