package gui;

import models.Post;
import models.User;
import storage.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PostFormScreen extends JFrame {

    private User loggedInUser;

    private JLabel title;
    private JLabel typeLabel;
    private JLabel descriptionLabel;
    private JComboBox<String> typeDropdown;
    private JTextArea descriptionArea;
    private JButton submitButton;
    private JButton backButton;

    public PostFormScreen(User user) {
        this.loggedInUser = user;

        setTitle("Neighborhood Help Exchange - Post Help");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        title = new JLabel("Create a Post");
        title.setBounds(180, 20, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));


        typeLabel = new JLabel("Post Type:");
        typeLabel.setBounds(50, 80, 100, 25);

        String[] types = {"REQUEST", "OFFER"};
        typeDropdown = new JComboBox<>(types);
        typeDropdown.setBounds(160, 80, 200, 25);


        descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(50, 130, 100, 25);

        descriptionArea = new JTextArea();
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(descriptionArea);
        descScroll.setBounds(160, 130, 270, 100);


        submitButton = new JButton("Submit Post");
        submitButton.setBounds(100, 270, 120, 30);

        backButton = new JButton("Back");
        backButton.setBounds(260, 270, 100, 30);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = (String) typeDropdown.getSelectedItem();
                String description = descriptionArea.getText().trim();


                if (description.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            PostFormScreen.this,
                            "Please enter a description",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }


                List<Post> posts = FileHandler.loadPosts();
                String postId = "P" + (posts.size() + 1);


                Post newPost = new Post(postId, loggedInUser.getUserId(), type, description, "OPEN");
                FileHandler.savePost(newPost);

                JOptionPane.showMessageDialog(
                        PostFormScreen.this,
                        "Post submitted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );


                new HomeScreen(loggedInUser);
                dispose();
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeScreen(loggedInUser);
                dispose();
            }
        });

        add(title);
        add(typeLabel);
        add(typeDropdown);
        add(descriptionLabel);
        add(descScroll);
        add(submitButton);
        add(backButton);

        setVisible(true);
    }
}