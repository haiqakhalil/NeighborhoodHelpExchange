package gui;

import models.User;

import javax.swing.*;
import java.awt.*;

public class NavBar {

    public static JPanel create(User user, String activeScreen, JFrame currentFrame) {

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(230, 230, 230));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel leftButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftButtons.setBackground(new Color(230, 230, 230));

        JButton postHelpButton = createButton("+ Post Help",
                activeScreen.equals("post") ?
                        new Color(20, 100, 20) : new Color(34, 139, 34));

        JButton communityButton = createButton("🏘 Community",
                activeScreen.equals("home") ?
                        new Color(60, 60, 60) : new Color(100, 100, 100));

        JButton myPostsButton = createButton("📋 My Posts",
                activeScreen.equals("dashboard") ?
                        new Color(40, 90, 140) : new Color(70, 130, 180));

        JButton matchButton = createButton("🤝 Matches",
                activeScreen.equals("match") ?
                        new Color(20, 100, 20) : new Color(34, 139, 34));

        JButton notifButton = createButton("🔔 Notifications",
                activeScreen.equals("notification") ?
                        new Color(180, 100, 0) : new Color(255, 140, 0));

        if (activeScreen.equals("post")) postHelpButton.setEnabled(false);
        if (activeScreen.equals("home")) communityButton.setEnabled(false);
        if (activeScreen.equals("dashboard")) myPostsButton.setEnabled(false);
        if (activeScreen.equals("match")) matchButton.setEnabled(false);
        if (activeScreen.equals("notification")) notifButton.setEnabled(false);

        leftButtons.add(postHelpButton);
        leftButtons.add(communityButton);
        leftButtons.add(myPostsButton);
        leftButtons.add(matchButton);
        leftButtons.add(notifButton);

        JButton logoutButton = createButton("🚪 Logout", new Color(178, 34, 34));

        bottomPanel.add(leftButtons, BorderLayout.WEST);
        bottomPanel.add(logoutButton, BorderLayout.EAST);

        postHelpButton.addActionListener(e -> {
            new PostFormScreen(user);
            currentFrame.dispose();
        });

        communityButton.addActionListener(e -> {
            new HomeScreen(user);
            currentFrame.dispose();
        });

        myPostsButton.addActionListener(e -> {
            new DashboardScreen(user);
            currentFrame.dispose();
        });

        matchButton.addActionListener(e -> {
            new MatchScreen(user);
            currentFrame.dispose();
        });

        notifButton.addActionListener(e -> {
            new NotificationScreen(user);
            currentFrame.dispose();
        });

        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    currentFrame,
                    "Are you sure you want to logout?",
                    "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                currentFrame.dispose();
                new LoginScreen();
            }
        });

        return bottomPanel;
    }

    private static JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(160, 38));
        return button;
    }
}