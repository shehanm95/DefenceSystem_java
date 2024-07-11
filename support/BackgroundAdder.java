package support;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

public class BackgroundAdder {
    /*seContentPane(panel) */
    public static void addBackground(JFrame frame ,ImageIcon imageIcon){
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);

            }
        };
        
        // Set the custom panel as the content pane
        frame.setContentPane(panel);
        frame.setSize(new Dimension(499, 400));
        System.out.println("image width : " + imageIcon.getIconWidth());
        frame.setResizable(false);
    }
    public static JPanel addBackground(ImageIcon imageIcon) {
        JPanel customPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        return customPanel;
    }
    public static JPanel addBackground(ImageIcon imageIcon,int width , int getHeight) {
        JPanel customPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        customPanel.setPreferredSize(new Dimension(width, getHeight));
        return customPanel;
    }
    public static void changeBackground(JPanel panel,ImageIcon imageIcon) {
        panel.setLayout(new BorderLayout());
        JPanel customPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.add(customPanel);
        
    }
}
