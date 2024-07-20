package support;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ProgressBar extends JPanel {
    private int width;
    private int maxWidth;
    private int height;
    private Color color;
    private int maxValue;
    public ProgressBar(int maxValue, int width, int height ,Color color){
        this.width = width;        
        this.maxWidth = width;        
        this.height = height;  
        this.color = color;          
        this.maxValue =maxValue;  
        setOpaque(false);        
    }
    public ProgressBar(int maxValue, int width, int height ,Color color , Color borderColor , int borderThick){
        this.width = width;        
        this.maxWidth = width;        
        this.height = height;  
        this.color = color;          
        this.maxValue =maxValue;
        this.setBorder(BorderFactory.createLineBorder(borderColor, borderThick));  
        setOpaque(false);        
    }

    // public void printComponent(Graphics g){
    //     super.printComponent(g);
    //     g.fillRect(0, 0, width,height);
    //     g.setColor(color);
    //     repaint();
    // }

    // public void changeValue(int value){
    //     width = (value /maxValue)*MaxWidth;
    //     this.revalidate();
    //     this.repaint();
    // }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, width, height);
    }

    public void changeValue(int value) {
        // Ensure value is within the valid range
        if (value < 0) value = 0;
        if (value > maxValue) value = maxValue;
        
        // Calculate the new width based on the value
        width = (int) ((value / (float) maxValue) * maxWidth);
        this.revalidate();
        this.repaint();
    }


}
