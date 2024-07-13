package support;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.*;

import intefaces.MapMarker;

public class GreenSelectorInMap extends JPanel implements MapMarker,ActionListener {
    Timer timer;
    int height,width;
    int sizer = 0;
    Image image1, image2,image3, image = null;
    public GreenSelectorInMap() {
       timer = new Timer(150,this);
       timer.start();
       image1  =new ImageIcon("./images/ring1.png").getImage();
       image2 = new ImageIcon("./images/ring2.png").getImage();
       image3 = new ImageIcon("./images/ring3.png").getImage();
    }


    

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0,0,24,24,this);
        repaint();
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if(sizer == 0){
            image = image1;
            sizer++;
        }else if(sizer ==1){
            image = image2;
            sizer++;
        }else{
            image = image3;
            sizer = 0;
        }


    }



    
}
