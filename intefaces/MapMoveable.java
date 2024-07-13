package intefaces;

import java.awt.Image;

import support.Position;

public interface MapMoveable {
    
    public void updatePosition();
    public int getIconHeight();
    public Image getImage();
    public int getX();
    public int getY();
    public Position setInitialPosition ();
}
