package com.bsoft.face.faceForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class FacePanelNorth {

    public static JPanel getJPanelNorth(Map cacheMap){
        JPanel jPanelNorth = new JPanel();

        jPanelNorth.add(new JLabel("身份证号",JLabel.LEFT));
        JTextField jTextField_sfzh=new JTextField(18);
        cacheMap.put("jTextField_sfzh",jTextField_sfzh);
        jPanelNorth.add(jTextField_sfzh);

        jPanelNorth.add(new JLabel("提示",JLabel.LEFT));
        JTextField jTextField_tips=new JTextField(25);
        cacheMap.put("jTextField_tips",jTextField_tips);
        jPanelNorth.add(jTextField_tips);

        return jPanelNorth;
    }
}
