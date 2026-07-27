package gui;

import models.Match;
import models.Post;
import models.User;
import storage.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MatchScreen extends JFrame {

    private User loggedInUser;
    private JPanel mainPanel;

    public MatchScreen(User user) {
        this.loggedInUser = user;

        setTitle("Neighborhood Help Exchange - My Matches");
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

        JLabel title = new JLabel("🤝 My Matches");
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

        loadMatches();

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(NavBar.create(loggedInUser, "match", this), BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadMatches() {
        mainPanel.removeAll();

        List<Match> allMatches = FileHandler.loadMatches();
        List<User> allUsers = FileHandler.loadUsers();
        List<Post> allPosts = FileHandler.loadPosts();

        boolean hasMatches = false;

        for (Match match : allMatches) {
            boolean isRequester = match.getRequesterId().equals(loggedInUser.getUserId());
            boolean isHelper = match.getHelperId().equals(loggedInUser.getUserId());

            if (!isRequester && !isHelper) continue;

            hasMatches = true;

            Post matchedPost = null;
            for (Post post : allPosts) {
                if (post.getPostId().equals(match.getPostId())) {
                    matchedPost = post;
                    break;
                }
            }

            String otherUserId = isRequester ?
                    match.getHelperId() : match.getRequesterId();
            User otherUser = null;
            for (User user : allUsers) {
                if (user.getUserId().equals(otherUserId)) {
                    otherUser = user;
                    break;
                }
            }

            if (matchedPost == null || otherUser == null) continue;

            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(12, 15, 12, 15)
            ));
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));
            card.setAlignmentX(Component.LEFT_ALIGNMENT);

            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            leftPanel.setBackground(Color.WHITE);

            String roleText = isRequester ?
                    "✅ Your post was helped by someone" :
                    "✅ You helped someone";
            JLabel roleLabel = new JLabel(roleText);
            roleLabel.setFont(new Font("Arial", Font.BOLD, 13));
            roleLabel.setForeground(new Color(34, 139, 34));
            roleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel descLabel = new JLabel("📝 Post: " + matchedPost.getDescription());
            descLabel.setFont(new Font("Arial", Font.PLAIN, 13));
            descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel nameLabel = new JLabel("👤 Name: " + otherUser.getName()
                    + "  |  @" + otherUser.getUsername()
                    + "  |  📍 " + otherUser.getArea());
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            nameLabel.setForeground(new Color(80, 80, 80));
            nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            leftPanel.add(roleLabel);
            leftPanel.add(Box.createVerticalStrut(6));
            leftPanel.add(descLabel);
            leftPanel.add(Box.createVerticalStrut(4));
            leftPanel.add(nameLabel);

            card.add(leftPanel, BorderLayout.CENTER);

            mainPanel.add(card);
            mainPanel.add(Box.createVerticalStrut(10));
        }

        if (!hasMatches) {
            JLabel noMatchLabel = new JLabel("No matches yet. Accept a post or wait for someone to accept yours!");
            noMatchLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            noMatchLabel.setForeground(Color.GRAY);
            mainPanel.add(noMatchLabel);
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }
}