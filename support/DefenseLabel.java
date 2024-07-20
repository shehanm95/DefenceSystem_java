package support;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import MainClass.DefenseSystem;

public class DefenseLabel extends JLabel {
    public DefenseLabel(String initialText , int fontSize){
        super(initialText.toUpperCase());
        setFont(new Font("" , 1, fontSize));
        this.setForeground(DefenseSystem.PrimaryfontColor);
    }

    public DefenseLabel(String initialText, int fontSize, Color color) {
        super(initialText.toUpperCase());
        setFont(new Font("" , 1, fontSize));
        this.setForeground(color);
    }
    public DefenseLabel(String initialText, int fontSize, Color color , int alignment) {
        super(initialText.toUpperCase());
        setFont(new Font("" , 1, fontSize));
        setHorizontalAlignment(alignment);
        this.setForeground(color);
    }
}
