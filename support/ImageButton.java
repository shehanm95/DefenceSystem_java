package support;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageButton extends JButton {
    private ImageIcon buttonIcon;

    public ImageButton(String BImageFileName) {
        buttonIcon = new ImageIcon(BImageFileName);
        this.setIcon(buttonIcon);
        this.setPreferredSize(new Dimension(234, 20));
        initialize();
    }

    public ImageButton(String BImageFileName, int autoHeight_PassAnyValue) {
        buttonIcon = new ImageIcon(BImageFileName);
        this.setIcon(buttonIcon);
        this.setPreferredSize(new Dimension(buttonIcon.getIconWidth(), buttonIcon.getIconHeight()));
        initialize();
    }

    public ImageButton(String BImageFileName, int width, int height) {
        buttonIcon = new ImageIcon(BImageFileName);
        this.setIcon(buttonIcon);
        this.setPreferredSize(new Dimension(width, height));
        initialize();
    }

    private void initialize() {
        this.setBackground(null);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        setRolloverEnabled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isEnabled()) {
            // Create a semi-transparent version of the icon
            Image disabledImage = createTransparentImage(buttonIcon.getImage(), 0.3f);
            g.drawImage(disabledImage, 0, 0, this);
        } else {
            super.paintComponent(g);
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Transparent border
        g.setColor(new Color(0, 0, 0, 0));
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    @Override
    public void updateUI() {
        super.updateUI();
        // Ensure that the look and feel changes don't affect our custom settings
        setRolloverEnabled(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
    }

    public int getButtonHeight() {
        return buttonIcon.getIconHeight();
    }

    public int getButtonWidth() {
        return buttonIcon.getIconWidth();
    }

    public ImageIcon getButtonIcon() {
        return buttonIcon;
    }

    private Image createTransparentImage(Image originalImage, float alpha) {
        BufferedImage transparentImage = new BufferedImage(
                originalImage.getWidth(null), originalImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = transparentImage.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(originalImage, 0, 0, null);
        g2d.dispose();
        return transparentImage;
    }
}
