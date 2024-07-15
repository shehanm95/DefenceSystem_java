package support;

import java.awt.Font;

import javax.swing.JLabel;

import MainClass.DefenseSystem;

public class DefenseLabel extends JLabel {
    public DefenseLabel(String initialText , int fontSize){
        super(initialText.toUpperCase());
        setFont(new Font("" , 1, fontSize));
        this.setForeground(DefenseSystem.PrimaryfontColor);
        
    }
}
