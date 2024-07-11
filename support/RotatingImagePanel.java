package support;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RotatingImagePanel extends JPanel implements ActionListener {
    private BufferedImage image;
    private Timer timer;
    private double angle = 0;

    public RotatingImagePanel(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        timer = new Timer(16, this); // 60 FPS
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, x, y);
        transform.translate(x - image.getWidth() / 2, y - image.getHeight() / 2);
        g2d.setTransform(transform);
        g2d.drawImage(image, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        angle += Math.toRadians(1); // Rotate by 1 degree
        repaint();
    }

   
}