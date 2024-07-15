package support;
import javax.swing.*;
import java.awt.*;

public class ImageButton extends JButton {
    private ImageIcon buttonIcon;
    public ImageButton(String BImageFileName){
        buttonIcon = new ImageIcon(BImageFileName);
        this.setIcon(buttonIcon);
        this.setPreferredSize(new Dimension(234, 20));
        this.setBackground(null);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(true);
        this.setOpaque(false);
    }
    public ImageButton(String BImageFileName, int autoHeight_PassAnyValue){
        buttonIcon = new ImageIcon(BImageFileName);
        this.setIcon(buttonIcon);
        this.setPreferredSize(new Dimension(buttonIcon.getIconWidth(), buttonIcon.getIconHeight()));
        this.setBackground(null);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(true);
        this.setOpaque(false);
    }
    public ImageButton(String BImageFileName, int width, int height){
        buttonIcon = new ImageIcon(BImageFileName);
        this.setIcon(buttonIcon);
        this.setPreferredSize(new Dimension(width  , height));
        this.setBackground(null);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(true);
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Ensure the button's background is transparent
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Ensure the border color is always black
        g.setColor(new Color(0,0,0,0));
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
    
    @Override
    public void updateUI() {
        super.updateUI();
        // Ensure that the look and feel changes don't affect our custom settings
        //setBorder(BorderFactory.createLineBorder(Color.BLACK));
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

    
}
