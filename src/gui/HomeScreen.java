package gui;

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
                // new PostFormScreen(loggedInUser);
                // dispose();
                JOptionPane.showMessageDialog(null, "Coming Soon!");
            }
        });

        dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // new DashboardScreen(loggedInUser);
                // dispose();
                JOptionPane.showMessageDialog(null, "Coming Soon!");
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

        List<Post> posts = FileHandler.loadPosts();

        for (Post post : posts) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.setMaximumSize(new Dimension(500, 90));
            card.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel typeLabel = new JLabel("Type: " + post.getType());
            JLabel descriptionLabel = new JLabel("Description: " + post.getDescription());
            JLabel statusLabel = new JLabel("Status: " + post.getStatus());

            card.add(typeLabel);
            card.add(descriptionLabel);
            card.add(statusLabel);

            postsPanel.add(card);
            postsPanel.add(Box.createVerticalStrut(8));
        }

        postsPanel.revalidate();
        postsPanel.repaint();
    }
}
