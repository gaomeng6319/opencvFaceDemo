package com.bsoft.face.faceForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Map;

public class FacePanelCenter extends JPanel {
    public BufferedImage mImg;

    @Override
    public void paintComponent(Graphics g){
        if(mImg!=null){
            g.drawImage(mImg, 0, 0, mImg.getWidth(),mImg.getHeight(),this);
        }
    }

    public static FacePanelCenter getJPanelCenter(Map cacheMap){
        FacePanelCenter facePanelCenter = new FacePanelCenter();
         facePanelCenter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                System.out.println("facePanelCenter -->click");
            }


        });
         return facePanelCenter;
    }

}
