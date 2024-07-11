package support;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
    private ImageIcon backgroundImage;

    public BackgroundPanel(ImageIcon imageIcon , Dimension dimension) {
        this.backgroundImage = imageIcon;
        setLayout(new BorderLayout());
        setPreferredSize(dimension);
    }
    public BackgroundPanel(ImageIcon imageIcon , int autoSize_Pass_Any_Value) {
        this.backgroundImage = imageIcon;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(imageIcon.getIconWidth()-2,imageIcon.getIconHeight()-2));
    }

    public void setBackgroundImage(ImageIcon imageIcon) {
        this.backgroundImage = imageIcon;
        repaint(); // Repaint the panel to update the background image
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Draw the background image
            g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        // TODO Auto-generated method stub
        super.setPreferredSize(preferredSize);
    }
}