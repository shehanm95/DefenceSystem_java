package support;

import java.awt.Dimension;

import javax.swing.JLabel;

public class JustifiedLabel extends JLabel{
    private int width;
    public JustifiedLabel(String text, int width) {
        super(text);
        this.width = width;
        setPreferredSize(new Dimension(width, getPreferredSize().height));
        setText(getJustifiedText(text, width));
    }

    private String getJustifiedText(String text, int width) {
        // Use HTML to simulate justification
        String htmlText = "<html><div style='width:" + width + "px; text-align: center;'>" + text + "</div></html>";
        return htmlText;
    }
    @Override
    public void setText(String text){
        text = getJustifiedText(text, width);
        super.setText(text);
    }
}
