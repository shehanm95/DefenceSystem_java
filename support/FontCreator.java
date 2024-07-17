package support;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;

public class FontCreator {
    public static Font create(File FontFile){
        try {
            File fontFile = FontFile;
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(20f);

            // Register the font
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            return customFont;
            

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}
