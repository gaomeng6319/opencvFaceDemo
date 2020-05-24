package com.bsoft.face.faceForm;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Map;

public class FacePanelSouth {

    public static JPanel getJPanelSouth( Map cacheMap){
        JFrame jf=(JFrame) cacheMap.get("jf");
        JTextField jTextField_sfzh=(JTextField)cacheMap.get("jTextField_sfzh");
        JTextField jTextField_tips=(JTextField)cacheMap.get("jTextField_tips");

        JPanel jPanelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton jButton1=new JButton("人脸采集");
        JButton jButton2=new JButton("人脸识别");
        jPanelSouth.add(jButton1);
        jPanelSouth.add(jButton2);
        jButton1 .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tips="";
                String sfzh=jTextField_sfzh.getText();
                //获取是否输入了身份证号
                if(sfzh==null||sfzh.length()==0){
                    tips="请输入将要采集人员的身份证号！";
                 //   JOptionPane.showMessageDialog(jf, tips,"提示",JOptionPane.WARNING_MESSAGE);
                    jTextField_tips.setText(tips);
                    return;
                }
                if(sfzh.length()!=15&&sfzh.length()!=18){
                    tips="身份证号格式错误！";
                  //  JOptionPane.showMessageDialog(jf, tips,"提示",JOptionPane.WARNING_MESSAGE);
                    jTextField_tips.setText(tips);
                    return;
                }
               System.out.println(new Date()+"开始采集人脸 身份证号:"+sfzh);
                tips="身份证号:"+sfzh+"开始采集人脸！";
              //  JOptionPane.showMessageDialog(jf, tips,"提示",JOptionPane.INFORMATION_MESSAGE);
                jTextField_tips.setText(tips);
                cacheMap.put("collectFace",true);
            }
        });
        return jPanelSouth;
    }
}
