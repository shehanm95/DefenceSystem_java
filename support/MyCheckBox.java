package support;

import java.awt.Font;

import javax.swing.JCheckBox;

import MainClass.DefenseSystem;


public class MyCheckBox extends JCheckBox {
    
    Font font  = new Font("", 1,12);
    public MyCheckBox(String labelText , boolean initialBool){
        super(labelText.toUpperCase(), initialBool);
        labelText = labelText.toUpperCase();
        setForeground(DefenseSystem.PrimaryfontColor);    
        setFont(font);
        setOpaque(false);
    }
    public MyCheckBox(String labelText , boolean initialBool, int fontSize){
        super(labelText.toUpperCase(), initialBool);
        labelText = labelText.toUpperCase();
        setForeground(DefenseSystem.PrimaryfontColor);        
        setFont(new Font("", 1, fontSize));
    }
}
