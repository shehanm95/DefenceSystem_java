package support;

import java.awt.Dimension;

import javax.swing.JLabel;

import enums.JustifiedLabelAlignment;

public class JustifiedLabel extends JLabel{
    private int width;
    JustifiedLabelAlignment JustifiedLabelAlignment;



    public JustifiedLabel(String text, int width) {
        super(text);
        this.width = width;
        setPreferredSize(new Dimension(width, getPreferredSize().height));
        setText(getJustifiedText(text, width));
    }


    public JustifiedLabel(String text, int width, JustifiedLabelAlignment JustifiedLabelAlignment) {
        super(text);
        this.JustifiedLabelAlignment = JustifiedLabelAlignment;
        setPreferredSize(new Dimension(width, getPreferredSize().height));
        setText(getJustifiedText(text, width , JustifiedLabelAlignment));
    }

    private String getJustifiedText(String text, int width) {
        // Use HTML to simulate justification
        String htmlText = "<html><div style='width:" + width + "px; text-align: center;'>" + text + "</div></html>";
        return htmlText;
    }
    private String getJustifiedText(String text, int width , JustifiedLabelAlignment JustifiedLabelAlignment) {
        // Use HTML to simulate justification
        String htmlText = "<html><div style='width:" + width + "px; text-align: left; vertical-align: top;'>" + text + "</div></html>";
        
        return htmlText;
    }


    @Override
    public void setText(String text){
        text = getJustifiedText(text, width);
        super.setText(text);
    }
    public void setText(String text , JustifiedLabelAlignment justifiedLabelAlignment){
        text = getJustifiedText(text, width , justifiedLabelAlignment);
        super.setText(text);
    }
}
